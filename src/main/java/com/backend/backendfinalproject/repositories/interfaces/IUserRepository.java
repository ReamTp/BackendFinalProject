package com.backend.backendfinalproject.repositories.interfaces;

import com.backend.backendfinalproject.models.Response;
import com.backend.backendfinalproject.models.User;

import java.util.List;

public interface IUserRepository {
    public List<User> getUsers();
    public Object getUser(int id);
    public Response remove(int id);
    public Response register(User user);
    public User getUserByCredentials(User user);
    public Response update(User user);
}
