package com.backend.backendfinalproject.controller;

import com.backend.backendfinalproject.models.Login;
import com.backend.backendfinalproject.models.User;
import com.backend.backendfinalproject.repositories.interfaces.IUserRepository;
import com.backend.backendfinalproject.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JWTUtil jwt;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public Login login(@RequestBody User user) {
        Login login = new Login();
        login.setResult(false);
        User loggedUser = userRepository.getUserByCredentials(user);
        if (loggedUser != null) {
            // Create Token
            String token = jwt.create(String.valueOf(loggedUser.getId()), loggedUser.getEmail());
            login.setToken(token);
            login.setResult(true);
        }
        return login;
    }
}
