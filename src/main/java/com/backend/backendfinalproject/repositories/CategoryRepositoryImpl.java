package com.backend.backendfinalproject.repositories;

import com.backend.backendfinalproject.models.Category;
import com.backend.backendfinalproject.models.Response;
import com.backend.backendfinalproject.repositories.interfaces.ICategoryRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CategoryRepositoryImpl implements ICategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Category> getCategories() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM Category WHERE state = 1", Category.class).getResultList();
    }

    @Override
    public Object getCategory(int id) {
        Session session = entityManager.unwrap(Session.class);
        List<Category> categoryList = session.createQuery("FROM Category WHERE id = :id AND state = 1", Category.class).setParameter("id", id).getResultList();
        return !categoryList.isEmpty() ? categoryList.get(0) : new Response("Category not found", false);
    }

    @Override
    public Response register(Category category) {
        Category newCategory = entityManager.merge(category);
        Response response = new Response("Registration Failed", false);

        if(newCategory != null) {
            response.setMessage("Registration Success");
            response.setStatus(true);
        }

        return response;
    }

    @Override
    public Response update(Category category) {
        Session session = entityManager.unwrap(Session.class);
        Response response = new Response("Update Failed", false);

        Query query = session.createQuery("UPDATE Category SET name = :name, description = :description, state = :state WHERE id = :id");
        query.setParameter("name", category.getName());
        query.setParameter("description", category.getDescription());
        query.setParameter("state", category.isState());
        query.setParameter("id", category.getId());

        if (query.executeUpdate() == 1) {
            response.setMessage("Update Success");
            response.setStatus(true);
        }

        return response;
    }

}
