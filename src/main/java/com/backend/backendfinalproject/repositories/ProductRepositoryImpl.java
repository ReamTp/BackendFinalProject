package com.backend.backendfinalproject.repositories;

import com.backend.backendfinalproject.models.City;
import com.backend.backendfinalproject.models.Product;
import com.backend.backendfinalproject.models.Response;
import com.backend.backendfinalproject.repositories.interfaces.IProductRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductRepositoryImpl implements IProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getProducts() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM Product WHERE state = 1", Product.class).getResultList();
    }

    @Override
    public Object getProduct(int id) {
        Session session = entityManager.unwrap(Session.class);
        List<Product> productList = session.createQuery("FROM Product WHERE id = :id AND state = 1", Product.class).setParameter("id", id).getResultList();
        return !productList.isEmpty() ? productList.get(0) : new Response("Product not found", false);
    }

    @Override
    public Response register(Product product) {
        Product newProduct = entityManager.merge(product);
        Response response = new Response("Registration Failed", false);

        if(newProduct != null) {
            response.setMessage("Registration Success");
            response.setStatus(true);
        }

        return response;
    }

    @Override
    public Response update(Product product) {
        Session session = entityManager.unwrap(Session.class);
        Response response = new Response("Update Failed", false);

        Query query = session.createQuery("UPDATE Product SET name = :name, detail = :detail, price = :price, category = :category, state = :state  WHERE id = :id");
        query.setParameter("name", product.getName());
        query.setParameter("detail", product.getDetail());
        query.setParameter("price", product.getPrice());
        query.setParameter("category", product.getCategory());
        query.setParameter("state", product.isState());
        query.setParameter("id", product.getId());

        if (query.executeUpdate() == 1) {
            response.setMessage("Update Success");
            response.setStatus(true);
        }

        return response;
    }
}
