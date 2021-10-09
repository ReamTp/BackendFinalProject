package com.backend.backendfinalproject.controller;

import com.backend.backendfinalproject.models.City;
import com.backend.backendfinalproject.models.Response;
import com.backend.backendfinalproject.repositories.interfaces.ICityRepository;
import com.backend.backendfinalproject.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping( value = "/register", method = RequestMethod.POST)
    public Response register(@RequestHeader(value = "Authorization") String token, @RequestBody City city) {
        return jwt.validateToken(token) ? cityRepository.register(city) : new Response("Register Failed", false);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Response update(@RequestHeader(value = "Authorization") String token, @RequestBody City city) {
        return jwt.validateToken(token) ? cityRepository.update(city) : new Response("Update Failed", false);
    }
}
