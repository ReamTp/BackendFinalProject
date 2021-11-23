package com.backend.backendfinalproject.controller;

import com.backend.backendfinalproject.models.Extra;
import com.backend.backendfinalproject.models.request.Response;
import com.backend.backendfinalproject.repositories.interfaces.IExtraRepository;
import com.backend.backendfinalproject.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/extras")
public class ExtraController {
    @Autowired
    private IExtraRepository extraRepository;
    @Autowired
    private JWTUtil jwt;

    @RequestMapping("")
    public List<Extra> getExtras() {
        return extraRepository.getExtras();
    }

    @RequestMapping("/{id}")
    public Object getExtra(@RequestHeader(value = "Authorization") String token, @PathVariable int id) {
        return jwt.validateToken(token) ? extraRepository.getExtra(id) : new Response("Extra not found", false);
    }

    @PostMapping( value = "/register")
    public Response register(@RequestHeader(value = "Authorization") String token, @RequestBody Extra extra) {
        return jwt.validateToken(token) ? extraRepository.register(extra) : new Response("Register Failed", false);
    }

    @PutMapping(value = "/update")
    public Response update(@RequestHeader(value = "Authorization") String token, @RequestBody Extra extra) {
        return jwt.validateToken(token) ? extraRepository.update(extra) : new Response("Update Failed", false);
    }
}
