package com.hopniel.gestionstock.service;

import com.hopniel.gestionstock.dto.CategoryDto;
import java.util.List;

public interface CategoryService {
    
    // Méthode pour récupérer toutes les catégories
    List<CategoryDto> findAll();
    
    // Méthodes existantes
    CategoryDto save(CategoryDto categoryDto);
    CategoryDto findById(Long id);
    void delete(Long id);  // Renommé pour correspondre à l'implémentation
}
