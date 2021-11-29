package com.backend.backendfinalproject.controller;

import com.backend.backendfinalproject.models.Category;
import com.backend.backendfinalproject.models.request.Response;
import com.backend.backendfinalproject.repositories.interfaces.ICategoryRepository;
import com.backend.backendfinalproject.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private JWTUtil jwt;

    @RequestMapping("")
    public List<Category> getCategories() {
        return categoryRepository.getCategories();
    }

    @RequestMapping("/{id}")
    public Object getCategory(@RequestHeader(value = "Authorization") String token, @PathVariable int id) {
        return jwt.validateToken(token) ? categoryRepository.getCategory(id) : new Response("Category not found", false);
    }

    @PostMapping(value = "/register")
    public Response register(@RequestHeader(value = "Authorization") String token, @RequestBody Category category) {
        return jwt.validateToken(token) ? categoryRepository.register(category) : new Response("Register Failed", false);
    }

    @PutMapping(value = "/update")
    public Response update(@RequestHeader(value = "Authorization") String token, @RequestBody Category category) {
        return jwt.validateToken(token) ? categoryRepository.update(category) : new Response("Update Failed", false);
    }

}
