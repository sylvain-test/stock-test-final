package com.hopniel.gestionstock.service.impl;

import com.hopniel.gestionstock.dto.CategoryDto;
import com.hopniel.gestionstock.model.entity.Category;
import com.hopniel.gestionstock.repository.CategoryRepository;
import com.hopniel.gestionstock.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = mapToEntity(categoryDto);
        category = categoryRepository.save(category);
        return mapToDto(category);
    }
    
    @Override
    public CategoryDto findById(Long id) {
        return categoryRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }
    
    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
    
    // Méthodes utilitaires pour la conversion entre entité et DTO
    private CategoryDto mapToDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        // Mapper d'autres champs si nécessaire
        return dto;
    }
    
    private Category mapToEntity(CategoryDto dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        // Mapper d'autres champs si nécessaire
        return category;
    }
}
