package com.backend.backendfinalproject.controller;

import com.backend.backendfinalproject.models.City;
import com.backend.backendfinalproject.models.request.Response;
import com.backend.backendfinalproject.repositories.interfaces.ICityRepository;
import com.backend.backendfinalproject.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private ICityRepository cityRepository;
    @Autowired
    private JWTUtil jwt;

    @RequestMapping("")
    public List<City> getCities() {
        return cityRepository.getCities();
    }

    @RequestMapping("/{id}")
    public Object getCity(@RequestHeader(value = "Authorization") String token, @PathVariable int id) {
        return jwt.validateToken(token) ? cityRepository.getCity(id) : new Response("City not found", false);
    }

    @PostMapping( value = "/register")
    public Response register(@RequestHeader(value = "Authorization") String token, @RequestBody City city) {
        return jwt.validateToken(token) ? cityRepository.register(city) : new Response("Register Failed", false);
    }

    @PutMapping(value = "/update")
    public Response update(@RequestHeader(value = "Authorization") String token, @RequestBody City city) {
        return jwt.validateToken(token) ? cityRepository.update(city) : new Response("Update Failed", false);
    }
}
