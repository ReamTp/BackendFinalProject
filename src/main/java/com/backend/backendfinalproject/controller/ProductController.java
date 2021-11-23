package com.backend.backendfinalproject.controller;

import com.backend.backendfinalproject.models.Product;
import com.backend.backendfinalproject.models.request.Response;
import com.backend.backendfinalproject.repositories.interfaces.IProductRepository;
import com.backend.backendfinalproject.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
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

    @PostMapping( value = "/register")
    public Response register(@RequestHeader(value = "Authorization") String token, @RequestBody Product product) {
        return jwt.validateToken(token) ? iProductRepository.register(product) : new Response("Register Failed", false);
    }

    @PutMapping(value = "/update")
    public Response update(@RequestHeader(value = "Authorization") String token, @RequestBody Product product) {
        return jwt.validateToken(token) ? iProductRepository.update(product) : new Response("Update Failed", false);
    }
}
