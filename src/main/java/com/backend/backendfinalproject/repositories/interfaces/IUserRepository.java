package com.backend.backendfinalproject.repositories.interfaces;

import com.backend.backendfinalproject.models.request.Response;
import com.backend.backendfinalproject.models.User;

import java.util.List;

public interface IUserRepository {
    public List<User> getUsers();
    public Object getUser(int id);
    public Response remove(int id);
    public User register(User user, String password);
    public User getUserByCredentials(User user);
    public Response update(User user);
}
