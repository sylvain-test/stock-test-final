package com.hopniel.gestionstock.config;

import com.hopniel.gestionstock.model.entity.Category;
import com.hopniel.gestionstock.model.entity.Product;
import com.hopniel.gestionstock.model.entity.Supplier;
import com.hopniel.gestionstock.repository.CategoryRepository;
import com.hopniel.gestionstock.repository.ProductRepository;
import com.hopniel.gestionstock.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;

    @Autowired
    public DataInitializer(CategoryRepository categoryRepository, SupplierRepository supplierRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initDatabase();
    }

    private void initDatabase() {
        // Check if data already exists
        if (categoryRepository.count() > 0) {
            return; // Skip initialization if data already exists
        }
        
        // Create categories
        Category electronics = new Category();
        electronics.setName("Electronics");
        electronics.setDescription("Electronic devices and accessories");
        electronics.setCreatedAt(LocalDateTime.now());
        electronics.setUpdatedAt(LocalDateTime.now());
        categoryRepository.save(electronics);

        Category clothing = new Category();
        clothing.setName("Clothing");
        clothing.setDescription("Apparel and fashion items");
        clothing.setCreatedAt(LocalDateTime.now());
        clothing.setUpdatedAt(LocalDateTime.now());
        categoryRepository.save(clothing);

        // Create suppliers
        Supplier supplier1 = new Supplier();
        supplier1.setName("Tech Supplies Inc.");
        supplier1.setEmail("contact@techsupplies.com");
        supplier1.setAddress("123 Tech Street, Silicon Valley");
        supplierRepository.save(supplier1);

        Supplier supplier2 = new Supplier();
        supplier2.setName("Fashion Wholesale Ltd.");
        supplier2.setEmail("info@fashionwholesale.com");
        supplier2.setAddress("456 Fashion Avenue, New York");
        supplierRepository.save(supplier2);

        // Create products
        Product laptop = new Product();
        laptop.setName("Laptop Pro X1");
        laptop.setDescription("High-performance laptop for professionals");
        laptop.setPurchasePrice(new BigDecimal("800.00"));
        laptop.setSellingPrice(new BigDecimal("1200.00"));
        laptop.setSku("TECH-LAPTOP-001");
        laptop.setStockQuantity(50);
        laptop.setMinStockLevel(10);
        laptop.setMaxStockLevel(100);
        laptop.setSupplier(supplier1);
        laptop.addCategory(electronics);
        productRepository.save(laptop);
    }
}
