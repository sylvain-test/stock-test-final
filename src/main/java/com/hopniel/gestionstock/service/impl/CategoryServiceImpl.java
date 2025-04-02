package com.hopniel.gestionstock.service.impl;

import com.hopniel.gestionstock.dto.CategoryCreateDTO;
import com.hopniel.gestionstock.dto.CategoryResponseDTO;
import com.hopniel.gestionstock.dto.CategoryUpdateDTO;
import com.hopniel.gestionstock.model.entity.Category;
import com.hopniel.gestionstock.model.entity.Product;
import com.hopniel.gestionstock.repository.CategoryRepository;
import com.hopniel.gestionstock.repository.ProductRepository;
import com.hopniel.gestionstock.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public CategoryResponseDTO createCategory(CategoryCreateDTO categoryCreateDTO) {
        Category category = new Category();
        category.setName(categoryCreateDTO.getName());
        category.setDescription(categoryCreateDTO.getDescription());
        
        Category savedCategory = categoryRepository.save(category);
        
        return convertToResponseDTO(savedCategory);
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        return categoryOpt.map(this::convertToResponseDTO).orElse(null);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryResponseDTO updateCategory(Long id, CategoryUpdateDTO categoryUpdateDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        
        category.setName(categoryUpdateDTO.getName());
        category.setDescription(categoryUpdateDTO.getDescription());
        
        Category updatedCategory = categoryRepository.save(category);
        return convertToResponseDTO(updatedCategory);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        
        // Remove category from all associated products
        List<Product> products = category.getProducts();
        if (products != null) {
            for (Product product : products) {
                product.removeCategory(category);
                productRepository.save(product);
            }
        }
        
        categoryRepository.delete(category);
    }
    
    private CategoryResponseDTO convertToResponseDTO(Category category) {
        CategoryResponseDTO responseDTO = new CategoryResponseDTO();
        responseDTO.setId(category.getId());
        responseDTO.setName(category.getName());
        responseDTO.setDescription(category.getDescription());
        responseDTO.setCreatedAt(category.getCreatedAt());
        responseDTO.setUpdatedAt(category.getUpdatedAt());
        
        // Set product count
        if (category.getProducts() != null) {
            responseDTO.setProductCount(category.getProducts().size());
        } else {
            responseDTO.setProductCount(0);
        }
        
        return responseDTO;
    }
}
