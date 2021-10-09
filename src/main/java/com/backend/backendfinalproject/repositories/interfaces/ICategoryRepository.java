package com.backend.backendfinalproject.repositories.interfaces;

import com.backend.backendfinalproject.models.Category;
import com.backend.backendfinalproject.models.City;
import com.backend.backendfinalproject.models.Response;

import java.util.List;

public interface ICategoryRepository {
    public List<Category> getCategories();
    public Object getCategory(int id);
    public Response register(Category category);
    public Response update(Category category);
}
