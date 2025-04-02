package com.hopniel.gestionstock.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoryUpdateDTO {
    @NotBlank(message = "Le nom de la catégorie est obligatoire")
    private String name;
    
    private String description;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
