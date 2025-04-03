package com.hopniel.gestionstock.controller;

import com.hopniel.gestionstock.dto.CategoryDto;
import com.hopniel.gestionstock.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllCategories() throws Exception {
        // Create test data
        CategoryDto category1 = new CategoryDto(1L, "Electronics", "Electronic devices");
        CategoryDto category2 = new CategoryDto(2L, "Clothing", "Apparel and accessories");
        List<CategoryDto> categories = Arrays.asList(category1, category2);

        // Mock service method
        when(categoryService.findAll()).thenReturn(categories);

        // Perform GET request and validate response
        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Electronics"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Clothing"));
    }

    // Add more test methods as needed
}
