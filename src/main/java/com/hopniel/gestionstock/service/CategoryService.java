package com.hopniel.gestionstock.service;

import com.hopniel.gestionstock.dto.CategoryCreateDTO;
import com.hopniel.gestionstock.dto.CategoryResponseDTO;
import com.hopniel.gestionstock.dto.CategoryUpdateDTO;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryCreateDTO categoryCreateDTO);
    CategoryResponseDTO getCategoryById(Long id);
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO updateCategory(Long id, CategoryUpdateDTO categoryUpdateDTO);
    void deleteCategory(Long id);
}
