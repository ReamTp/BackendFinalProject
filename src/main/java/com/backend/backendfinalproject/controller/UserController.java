package com.backend.backendfinalproject.controller;

import com.backend.backendfinalproject.models.User;
import com.backend.backendfinalproject.repositories.IUserRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserRepository userRepository;

    @RequestMapping("/")
    public List<User> getUsers(){
        return userRepository.getUsers();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void register(@RequestBody User user){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        // hash(n_interacciones, memoria, numero de hilos, texto)
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);

        userRepository.register(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id){
        userRepository.remove(id);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean validateCredentials(@RequestBody User user){
        return userRepository.validateCredentials(user);
    }
}
