package com.backend.backendfinalproject.controller;

import com.backend.backendfinalproject.models.request.LoginResponse;
import com.backend.backendfinalproject.models.request.Response;
import com.backend.backendfinalproject.models.User;
import com.backend.backendfinalproject.models.UserDataRegister;
import com.backend.backendfinalproject.models.response.UserResponse;
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
    public List<UserResponse> getUsers(@RequestHeader(value = "Authorization") String token){
        return jwt.validateToken(token) ? userRepository.getUsers(): null;
    }

    @PostMapping("/token")
    public Object getUserDataWithToken(@RequestHeader(value = "Authorization") String token) {
        if(jwt.validateToken(token)) {
            String id = jwt.getValue(token);
            return userRepository.getUserWithToken(id);
        }
        return new Response("Invalid credentials", false);
    }

    @PostMapping("/{id}")
    public Object getUser(@RequestHeader(value = "Authorization") String token, @PathVariable int id) {
        return jwt.validateToken(token) ? userRepository.getUser(id) : new Response("User not found", false);
    }

    @PostMapping(value = "/insert")
    public LoginResponse register(@RequestBody UserDataRegister user){
        String password = user.getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        // hash(n_interacciones, memoria, numero de hilos, texto)
        String hash = argon2.hash(1, 1024, 1, password);
        user.setPassword(hash);

        UserResponse newUser = userRepository.register(new User(user.getName(), user.getLastName(), user.getEmail(), user.getPassword()), password);
        LoginResponse login = new LoginResponse();

        if (newUser != null) {
            // Create Token
            String token = jwt.create(String.valueOf(newUser.getId()), newUser.getEmail());
            login.setToken(token);
            login.setAbv((""+newUser.getName().toCharArray()[0]+newUser.getLastName().toCharArray()[0]).toUpperCase());
            login.setResult(true);
        }

        return login;
    }

    @DeleteMapping(value = "/{id}")
    public Response delete(@RequestHeader(value = "Authorization") String token, @PathVariable int id){
        return jwt.validateToken(token) ? userRepository.remove(id) : new Response("Delete Failed", false);
    }

    @PostMapping("/close-account")
    public Response closeAccount(@RequestHeader(value = "Authorization") String token) {
        if(jwt.validateToken(token)) {
            int id = Integer.parseInt(jwt.getValue(token));
            return userRepository.closeAccount(id);
        }

        return new Response("Token invalido", false);
    }

    @PutMapping("/update/direction")
    public Response changeDirection(@RequestHeader(value = "Authorization") String token, @RequestBody UserResponse user) {
        if(jwt.validateToken(token)) {
            int id = Integer.parseInt(jwt.getValue(token));
            return userRepository.changeDirection(id, user.getDirection());
        }

        return new Response("No se puedo actualizar la dirección", false);
    }

    @PutMapping(value = "/update")
    public Response update(@RequestHeader(value = "Authorization") String token, @RequestBody UserResponse user) {
        if(jwt.validateToken(token)) {
            String id = jwt.getValue(token);
            return userRepository.update(user, id);
        }
        return new Response("Update Failed", false);
    }
}
