package com.backend.backendfinalproject.controller;

import com.backend.backendfinalproject.models.request.LoginResponse;
import com.backend.backendfinalproject.models.request.Response;
import com.backend.backendfinalproject.models.User;
import com.backend.backendfinalproject.models.UserDataRegister;
import com.backend.backendfinalproject.repositories.interfaces.IUserRepository;
import com.backend.backendfinalproject.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping(value = "/insert")
    public LoginResponse register(@RequestBody UserDataRegister user){
        String password = user.getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        // hash(n_interacciones, memoria, numero de hilos, texto)
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);

        User newUser = userRepository.register(new User(user.getName(), user.getLastName(), user.getEmail(), user.getPassword()), password);
        LoginResponse login = new LoginResponse();

        if (newUser != null) {
            // Create Token
            String token = jwt.create(String.valueOf(newUser.getId()), newUser.getEmail());
            login.setToken(token);
            login.setAbv((""+newUser.getName().toCharArray()[0]+newUser.getLast_name().toCharArray()[0]).toUpperCase());
            login.setResult(true);
        }

        return login;
    }

    @DeleteMapping(value = "/{id}")
    public Response delete(@RequestHeader(value = "Authorization") String token, @PathVariable int id){
        return jwt.validateToken(token) ? userRepository.remove(id) : new Response("Delete Failed", false);
    }

    @PutMapping(value = "/update")
    public Response update(@RequestHeader(value = "Authorization") String token, @RequestBody User user) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        // hash(n_interacciones, memoria, numero de hilos, texto)
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);

        return jwt.validateToken(token) ? userRepository.update(user): new Response("Update Failed", false);
    }
}
