package com.backend.backendfinalproject.repositories;

import com.backend.backendfinalproject.models.request.LoginRequest;
import com.backend.backendfinalproject.models.request.Response;
import com.backend.backendfinalproject.models.User;
import com.backend.backendfinalproject.models.response.UserResponse;
import com.backend.backendfinalproject.repositories.interfaces.IUserRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl implements IUserRepository {
    @PersistenceContext
    // Sirve para hacer conexion con la DB
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<UserResponse> getUsers() {
        Session currentSession = entityManager.unwrap(Session.class);
        String query = "FROM User";
        List<User> userList = currentSession.createQuery(query, User.class).getResultList();

        List<UserResponse> newList = new ArrayList<>();
        userList.forEach(user -> {
            newList.add(new UserResponse(user.getId(), user.getName(), user.getLastName(), user.getPhone(), user.getDirection(), user.getCity(), user.getEmail(), "", user.getSex(), user.getAvatar(), user.getState()));
        });

        return newList;
    }

    @Override
    public Object getUser(int id) {
        Session session = entityManager.unwrap(Session.class);
        List<User> usersList = session.createQuery("FROM User WHERE id = :id", User.class).setParameter("id", id).getResultList();
        return !usersList.isEmpty() ? usersList.get(0) : new Response("User not found", false);
    }

    @Override
    public UserResponse getUserWithToken(String id) {
        Session session = entityManager.unwrap(Session.class);
        List<User> listUsers = session.createQuery("FROM User WHERE id = :id", User.class)
                .setParameter("id", Integer.parseInt(id))
                .getResultList();

        if (listUsers.isEmpty()) return null;

        User user = listUsers.get(0);
        return new UserResponse(user.getId(), user.getName(), user.getLastName(), user.getPhone(), user.getDirection(), user.getCity(), user.getEmail(), "", user.getSex(), user.getAvatar(), user.getState());
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
    public Response closeAccount(int id) {
        Session session = entityManager.unwrap(Session.class);
        Response response = new Response();
        response.setStatus(false);

        Query query = session.createQuery("UPDATE User SET state = false WHERE id = :id");
        query.setParameter("id", id);

        int status = query.executeUpdate();

        if (status == 1) {
            response.setMessage("Cuenta Cerrada");
            response.setStatus(true);
        } else {
            response.setMessage("Update Failed");
        }

        return response;
    }

    @Override
    public Response changeDirection(int id, String direction) {
        Session session = entityManager.unwrap(Session.class);
        Response response = new Response();
        response.setStatus(false);

        Query query = session.createQuery("UPDATE User SET direction = :direction WHERE id = :id");
        query.setParameter("direction", direction);
        query.setParameter("id", id);

        int status = query.executeUpdate();

        if (status == 1) {
            response.setMessage("Dirección Cambiada");
            response.setStatus(true);
        } else {
            response.setMessage("Error al completar operación");
        }

        return response;
    }

    @Override
    public UserResponse register(User user, String password) {
        User newUser = entityManager.merge(user);

        if (newUser != null) {
            return getUserByCredentials(new LoginRequest(newUser.getEmail(), password));
        }
        return null;
    }

    @Override
    public UserResponse getUserByCredentials(LoginRequest user) {
        Session session = entityManager.unwrap(Session.class);
        List<User> listUsers = session.createQuery("FROM User WHERE email = :email", User.class)
                .setParameter("email", user.getEmail())
                .getResultList();

        if (listUsers.isEmpty()) return null;

        String passwordHashed = listUsers.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        User newUser = listUsers.get(0);
        UserResponse userResponse = new UserResponse(newUser.getId(), newUser.getName(), newUser.getLastName(), newUser.getPhone(), newUser.getDirection(), newUser.getCity(), newUser.getEmail(), "", newUser.getSex(), newUser.getAvatar(), newUser.getState());

        return argon2.verify(passwordHashed, user.getPassword()) ? userResponse : null;
    }

    @Override
    public Response update(UserResponse user, String id) {
        Session session = entityManager.unwrap(Session.class);
        Response response = new Response();
        response.setStatus(false);

        Query query = session.createQuery("UPDATE User SET avatar= :avatar, direction = :direction, email = :email, lastName = :lastName, name = :name, phone = :phone, sex = :sex, city = :city WHERE id = :id");
        query.setParameter("avatar", "");
        query.setParameter("direction", user.getDirection());
        query.setParameter("email", user.getEmail());
        query.setParameter("lastName", user.getLastName());
        query.setParameter("name", user.getName());
        query.setParameter("phone", user.getPhone());
        query.setParameter("sex", user.getSex());
        query.setParameter("city", user.getCity());
        query.setParameter("id", Integer.parseInt(id));

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
