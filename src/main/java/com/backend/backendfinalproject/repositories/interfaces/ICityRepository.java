package com.backend.backendfinalproject.repositories.interfaces;

import com.backend.backendfinalproject.models.request.Response;
import com.backend.backendfinalproject.models.City;

import java.util.List;

public interface ICityRepository {
    //metodos
    public List<City> getCities();
    public Object getCity(int id);
    public Response register(City city);
    public Response update(City city);
}
