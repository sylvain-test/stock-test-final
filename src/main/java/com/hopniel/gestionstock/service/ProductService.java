package com.hopniel.gestionstock.service;

import com.hopniel.gestionstock.dto.ProductCreateDTO;
import com.hopniel.gestionstock.dto.ProductResponseDTO;
import com.hopniel.gestionstock.dto.ProductUpdateDTO;
import com.hopniel.gestionstock.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductResponseDTO createProduct(ProductCreateDTO productCreateDTO);
    ProductResponseDTO getProductById(Long id);
    ProductResponseDTO getProductBySku(String sku);
    List<ProductResponseDTO> getAllProducts();
    List<ProductResponseDTO> getLowStockProducts(int threshold);
    ProductResponseDTO updateProduct(Long id, ProductUpdateDTO productUpdateDTO);
    ProductResponseDTO updateStock(Long id, Integer quantity);
    void deleteProduct(Long id);
    List<ProductResponseDTO> getLowStockProductsAlert();
}
