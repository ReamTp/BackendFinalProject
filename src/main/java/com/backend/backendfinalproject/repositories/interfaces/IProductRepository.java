package com.backend.backendfinalproject.repositories.interfaces;
import com.backend.backendfinalproject.models.Product;
import com.backend.backendfinalproject.models.request.Response;

import java.util.List;

public interface IProductRepository {
    List<Product> getProducts();
    List<Product> getProductsByCategory(int id);
    List<Product> getProductsBySearch(String search);
    Object getProduct(int id);
    Response register(Product product);
    Response update(Product product);
}
