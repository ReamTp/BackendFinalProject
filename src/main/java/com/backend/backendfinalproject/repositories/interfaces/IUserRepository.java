package com.backend.backendfinalproject.repositories.interfaces;

import com.backend.backendfinalproject.models.request.LoginRequest;
import com.backend.backendfinalproject.models.request.Response;
import com.backend.backendfinalproject.models.User;
import com.backend.backendfinalproject.models.response.UserResponse;

import java.util.List;

public interface IUserRepository {
    List<UserResponse> getUsers();
    Object getUser(int id);
    UserResponse getUserWithToken(String token);
    Response remove(int id);
    Response closeAccount(int id);
    Response changeDirection(int id, String direction);
    UserResponse register(User user, String password);
    UserResponse getUserByCredentials(LoginRequest user);
    Response update(UserResponse user, String email);
}
