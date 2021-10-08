package com.backend.backendfinalproject.repositories;

import com.backend.backendfinalproject.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public void remove(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void register(User user) {
        entityManager.merge(user);
    }

    @Override
    public boolean validateCredentials(User user) {
        Session session = entityManager.unwrap(Session.class);
        List<User> listUsers = session.createQuery("FROM User WHERE email = :email", User.class)
                .setParameter("email", user.getEmail())
                .getResultList();

        if (listUsers.isEmpty()) return false;

        String passwordHashed = listUsers.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        return argon2.verify(passwordHashed, user.getPassword());
    }
}
