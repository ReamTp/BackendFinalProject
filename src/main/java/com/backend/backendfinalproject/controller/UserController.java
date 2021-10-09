package com.backend.backendfinalproject.controller;

import com.backend.backendfinalproject.models.Response;
import com.backend.backendfinalproject.models.User;
import com.backend.backendfinalproject.repositories.interfaces.IUserRepository;
import com.backend.backendfinalproject.utils.JWTUtil;
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
    @Autowired
    private JWTUtil jwt;

    @RequestMapping("")
    public List<User> getUsers(@RequestHeader(value = "Authorization") String token){
        return jwt.validateToken(token) ? userRepository.getUsers(): null;
    }

    @RequestMapping("/{id}")
    public Object getUser(@RequestHeader(value = "Authorization") String token, @PathVariable int id) {
        return jwt.validateToken(token) ? userRepository.getUser(id) : new Response("User not found", false);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response register(@RequestHeader(value = "Authorization") String token, @RequestBody User user){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        // hash(n_interacciones, memoria, numero de hilos, texto)
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);
        return jwt.validateToken(token) ? userRepository.register(user) : new Response("Register Failed", false);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Response delete(@RequestHeader(value = "Authorization") String token, @PathVariable int id){
        return jwt.validateToken(token) ? userRepository.remove(id) : new Response("Delete Failed", false);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Response update(@RequestHeader(value = "Authorization") String token, @RequestBody User user) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        // hash(n_interacciones, memoria, numero de hilos, texto)
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);

        return jwt.validateToken(token) ? userRepository.update(user): new Response("Update Failed", false);
    }
}
