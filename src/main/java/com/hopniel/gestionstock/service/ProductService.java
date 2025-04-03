package com.hopniel.gestionstock.service;

import com.hopniel.gestionstock.model.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    List<Product> getProductsByCategory(Long categoryId);
    Product saveProduct(Product product);
    void deleteProduct(Long id);
    List<Product> findAll();  // Add this method
}
