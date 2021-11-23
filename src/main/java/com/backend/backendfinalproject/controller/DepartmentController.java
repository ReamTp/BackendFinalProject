package com.backend.backendfinalproject.controller;

import com.backend.backendfinalproject.models.Department;
import com.backend.backendfinalproject.models.request.Response;
import com.backend.backendfinalproject.repositories.interfaces.IDepartmentRepository;
import com.backend.backendfinalproject.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/departments")
public class DepartmentController{
    @Autowired
    private IDepartmentRepository departmentRepository;
    @Autowired
    private JWTUtil jwt;

    @RequestMapping("")
    public List<Department> getDepartments() {
        return departmentRepository.getDepartments();
    }

    @RequestMapping("/{id}")
    public Object getDepartment(@RequestHeader(value = "Authorization") String token, @PathVariable int id) {
        return jwt.validateToken(token) ? departmentRepository.getDepartment(id) : new Response("Department not found", false);
    }

    @PostMapping(value = "/register")
    public Response register(@RequestHeader(value = "Authorization") String token, @RequestBody Department department) {
        return jwt.validateToken(token) ? departmentRepository.register(department) : new Response("Register Failed", false);
    }

    @PutMapping(value = "/update")
    public Response update(@RequestHeader(value = "Authorization") String token, @RequestBody Department department) {
        return jwt.validateToken(token) ? departmentRepository.update(department) : new Response("Update Failed", false);
    }
}
