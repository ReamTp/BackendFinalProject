package com.backend.backendfinalproject.controller;

import com.backend.backendfinalproject.models.City;
import com.backend.backendfinalproject.models.Product;
import com.backend.backendfinalproject.models.Response;
import com.backend.backendfinalproject.repositories.interfaces.IProductRepository;
import com.backend.backendfinalproject.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductController {

    @Autowired
    private IProductRepository iProductRepository;
    @Autowired
    private JWTUtil jwt;

    @RequestMapping("")
    public List<Product> getProducts() {
        return iProductRepository.getProducts();
    }

    @RequestMapping("/{id}")
    public Object getProduct(@RequestHeader(value = "Authorization") String token, @PathVariable int id) {
        return jwt.validateToken(token) ? iProductRepository.getProduct(id) : new Response("Product not found", false);
    }

    @RequestMapping( value = "/register", method = RequestMethod.POST)
    public Response register(@RequestHeader(value = "Authorization") String token, @RequestBody Product product) {
        return jwt.validateToken(token) ? iProductRepository.register(product) : new Response("Register Failed", false);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Response update(@RequestHeader(value = "Authorization") String token, @RequestBody Product product) {
        return jwt.validateToken(token) ? iProductRepository.update(product) : new Response("Update Failed", false);
    }

}
