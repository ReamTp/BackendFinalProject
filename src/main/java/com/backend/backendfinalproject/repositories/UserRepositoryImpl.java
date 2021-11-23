package com.backend.backendfinalproject.repositories;

import com.backend.backendfinalproject.models.request.Response;
import com.backend.backendfinalproject.models.User;
import com.backend.backendfinalproject.repositories.interfaces.IUserRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl implements IUserRepository {
    @PersistenceContext
    // Sirve para hacer conexion con la DB
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<User> getUsers() {
        Session currentSession = entityManager.unwrap(Session.class);
        String query = "FROM User";
        return currentSession.createQuery(query, User.class).getResultList();
    }

    @Override
    public Object getUser(int id) {
        Session session = entityManager.unwrap(Session.class);
        List<User> usersList = session.createQuery("FROM User WHERE id = :id", User.class).setParameter("id", id).getResultList();
        return !usersList.isEmpty() ? usersList.get(0) : new Response("User not found", false);
    }

    @Override
    public Response remove(int id) {
        User user = entityManager.find(User.class, id);
        Response response = new Response();
        response.setStatus(false);

        if (user != null) {
            response.setStatus(true);
            response.setMessage("Complete deletion");
            entityManager.remove(user);
        } else {
            response.setMessage("Failed deletion");
        }

        return response;
    }

    @Override
    public User register(User user, String password) {
        User newUser = entityManager.merge(user);

        if (newUser != null) {
            user.setPassword(password);
            return getUserByCredentials(user);
        }
        return null;
    }

    @Override
    public User getUserByCredentials(User user) {
        Session session = entityManager.unwrap(Session.class);
        List<User> listUsers = session.createQuery("FROM User WHERE email = :email", User.class)
                .setParameter("email", user.getEmail())
                .getResultList();

        if (listUsers.isEmpty()) return null;

        String passwordHashed = listUsers.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        return argon2.verify(passwordHashed, user.getPassword()) ? listUsers.get(0) : null;
    }

    @Override
    public Response update(User user) {
        Session session = entityManager.unwrap(Session.class);
        Response response = new Response();
        response.setStatus(false);

        Query query = session.createQuery("UPDATE User SET avatar= :avatar, direction = :direction, email = :email, last_name = :last_name, name = :name, password = :pass, phone = :phone, sex = :sex, state = :state, city = :city WHERE id = :id");
        query.setParameter("avatar", user.getAvatar());
        query.setParameter("direction", user.getDirection());
        query.setParameter("email", user.getEmail());
        query.setParameter("last_name", user.getLast_name());
        query.setParameter("name", user.getName());
        query.setParameter("pass", user.getPassword());
        query.setParameter("phone", user.getPhone());
        query.setParameter("sex", user.getSex());
        query.setParameter("state", user.getState());
        query.setParameter("city", user.getCity());
        query.setParameter("id", user.getId());

        int status = query.executeUpdate();

        if (status == 1) {
            response.setMessage("Update Success");
            response.setStatus(true);
        } else {
            response.setMessage("Update Failed");
        }

        return response;
    }
}
