package com.backend.backendfinalproject.repositories;

import com.backend.backendfinalproject.models.City;
import com.backend.backendfinalproject.models.request.Response;
import com.backend.backendfinalproject.repositories.interfaces.ICityRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CityRepositoryImpl implements ICityRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<City> getCities() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM City WHERE state = 1", City.class).getResultList();
    }

    @Override
    public Object getCity(int id) {
        Session session = entityManager.unwrap(Session.class);
        List<City> cityList = session.createQuery("FROM City WHERE id = :id AND state = 1", City.class).setParameter("id", id).getResultList();
        return !cityList.isEmpty() ? cityList.get(0) : new Response("City not found", false);
    }

    @Override
    public Response register(City city) {
        City newCity = entityManager.merge(city);
        Response response = new Response("Registration Failed", false);

        if(newCity != null) {
            response.setMessage("Registration Success");
            response.setStatus(true);
        }

        return response;
    }

    @Override
    public Response update(City city) {
        Session session = entityManager.unwrap(Session.class);
        Response response = new Response("Update Failed", false);

        Query query = session.createQuery("UPDATE City SET name = :name, state = :state, department = :department WHERE id = :id");
        query.setParameter("name", city.getName());
        query.setParameter("state", city.getState());
        query.setParameter("department", city.getDepartment());
        query.setParameter("id", city.getId());

        if (query.executeUpdate() == 1) {
            response.setMessage("Update Success");
            response.setStatus(true);
        }

        return response;
    }
}
