package com.backend.backendfinalproject.repositories.interfaces;

import com.backend.backendfinalproject.models.Response;
import com.backend.backendfinalproject.models.Department;

import java.util.List;

public interface IDepartmentRepository {
    public List<Department> getDepartments();
    public Object getDepartment(int id);
    public Response register(Department department);
    public Response update(Department department);
}
