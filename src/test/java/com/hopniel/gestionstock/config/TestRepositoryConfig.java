package com.hopniel.gestionstock.config;

import com.hopniel.gestionstock.repository.CategoryRepository;
import com.hopniel.gestionstock.repository.ProductRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestRepositoryConfig {

    @MockBean
    private CategoryRepository categoryRepository;
    
    @MockBean
    private ProductRepository productRepository;
    
    @Bean
    public CategoryRepository categoryRepository() {
        return categoryRepository;
    }
    
    @Bean
    public ProductRepository productRepository() {
        return productRepository;
    }
}
