package com.hopniel.gestionstock.service.impl;

import com.hopniel.gestionstock.dto.ProductCreateDTO;
import com.hopniel.gestionstock.dto.ProductResponseDTO;
import com.hopniel.gestionstock.dto.ProductUpdateDTO;
import com.hopniel.gestionstock.model.entity.Product;
import com.hopniel.gestionstock.repository.ProductRepository;
import com.hopniel.gestionstock.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public ProductResponseDTO createProduct(ProductCreateDTO productCreateDTO) {
        // Convert DTO to entity
        Product product = new Product();
        product.setName(productCreateDTO.getName());
        product.setSku(productCreateDTO.getSku());
        product.setDescription(productCreateDTO.getDescription());
        product.setPrice(productCreateDTO.getPrice());
        product.setPurchasePrice(productCreateDTO.getPurchasePrice());
        product.setSellingPrice(productCreateDTO.getSellingPrice());
        product.setStockQuantity(productCreateDTO.getStockQuantity());
        product.setMinStockLevel(productCreateDTO.getMinStockLevel());
        product.setMaxStockLevel(productCreateDTO.getMaxStockLevel());
        
        // Save product
        Product savedProduct = productRepository.save(product);
        
        // Convert entity to response DTO
        return convertToResponseDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Optional<Product> productOpt = productRepository.findById(id);
        return productOpt.map(this::convertToResponseDTO).orElse(null);
    }

    @Override
    public ProductResponseDTO getProductBySku(String sku) {
        Optional<Product> productOpt = productRepository.findBySku(sku);
        return productOpt.map(this::convertToResponseDTO).orElse(null);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDTO> getLowStockProducts(int threshold) {
        List<Product> products = productRepository.findByStockQuantityLessThanEqual(threshold);
        return products.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<ProductResponseDTO> getLowStockProductsAlert() {
        List<Product> products = productRepository.findByStockQuantityLessThanMinStockLevel();
        return products.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductUpdateDTO productUpdateDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        product.setName(productUpdateDTO.getName());
        product.setSku(productUpdateDTO.getSku());
        product.setDescription(productUpdateDTO.getDescription());
        product.setPrice(productUpdateDTO.getPrice());
        product.setPurchasePrice(productUpdateDTO.getPurchasePrice());
        product.setSellingPrice(productUpdateDTO.getSellingPrice());
        product.setStockQuantity(productUpdateDTO.getStockQuantity());
        product.setMinStockLevel(productUpdateDTO.getMinStockLevel());
        product.setMaxStockLevel(productUpdateDTO.getMaxStockLevel());
        
        Product updatedProduct = productRepository.save(product);
        return convertToResponseDTO(updatedProduct);
    }

    @Override
    @Transactional
    public ProductResponseDTO updateStock(Long id, Integer quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        product.setStockQuantity(quantity);
        
        Product updatedProduct = productRepository.save(product);
        return convertToResponseDTO(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
    private ProductResponseDTO convertToResponseDTO(Product product) {
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setId(product.getId());
        responseDTO.setName(product.getName());
        responseDTO.setSku(product.getSku());
        responseDTO.setDescription(product.getDescription());
        responseDTO.setPrice(product.getPrice());
        responseDTO.setPurchasePrice(product.getPurchasePrice());
        responseDTO.setSellingPrice(product.getSellingPrice());
        responseDTO.setStockQuantity(product.getStockQuantity());
        responseDTO.setMinStockLevel(product.getMinStockLevel());
        responseDTO.setMaxStockLevel(product.getMaxStockLevel());
        responseDTO.setCreatedAt(product.getCreatedAt());
        responseDTO.setUpdatedAt(product.getUpdatedAt());
        // Set category if needed
        return responseDTO;
    }
}
