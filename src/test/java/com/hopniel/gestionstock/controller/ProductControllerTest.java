package com.hopniel.gestionstock.controller;

import com.hopniel.gestionstock.model.entity.Product;
import com.hopniel.gestionstock.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        // Create test products
        Product product1 = new Product("Test Product 1", "Description 1", new BigDecimal("10.00"), new BigDecimal("20.00"));
        product1.setSku("SKU001");
        Product product2 = new Product("Test Product 2", "Description 2", new BigDecimal("15.00"), new BigDecimal("30.00"));
        product2.setSku("SKU002");
        
        List<Product> products = Arrays.asList(product1, product2);
        
        // Mock the service method
        when(productService.findAll()).thenReturn(products);
        
        // Perform the test
        mockMvc.perform(get("/api/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Product 1"))
                .andExpect(jsonPath("$[1].name").value("Test Product 2"));
        
        // Verify the service method was called
        verify(productService, times(1)).findAll();
    }

    @Test
    public void testGetProductById() throws Exception {
        // Create a test product
        Product product = new Product("Test Product", "Description", new BigDecimal("10.00"), new BigDecimal("20.00"));
        product.setSku("SKU003");
        
        // Mock the service method
        when(productService.getProductById(1L)).thenReturn(Optional.of(product));
        
        // Perform the test
        mockMvc.perform(get("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Product"));
        
        // Verify the service method was called
        verify(productService, times(1)).getProductById(1L);
    }
}
