package com.backend.backendfinalproject.repositories.interfaces;
import com.backend.backendfinalproject.models.Product;
import com.backend.backendfinalproject.models.request.Response;

import java.util.List;

public interface IProductRepository {
    public List<Product> getProducts();
    public Object getProduct(int id);
    public Response register(Product product);
    public Response update(Product product);
}
