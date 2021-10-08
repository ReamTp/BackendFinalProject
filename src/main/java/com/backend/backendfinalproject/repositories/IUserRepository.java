package com.backend.backendfinalproject.repositories;

import com.backend.backendfinalproject.models.User;

import java.util.List;

public interface IUserRepository {
    public List<User> getUsers();
    public void remove(int id);
    public void register(User user);
    public boolean validateCredentials(User user);
}
