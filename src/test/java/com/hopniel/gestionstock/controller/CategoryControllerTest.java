package com.hopniel.gestionstock.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hopniel.gestionstock.config.TestSecurityConfig;
import com.hopniel.gestionstock.dto.CategoryCreateDTO;
import com.hopniel.gestionstock.dto.CategoryResponseDTO;
import com.hopniel.gestionstock.dto.CategoryUpdateDTO;
import com.hopniel.gestionstock.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
@Import(TestSecurityConfig.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    private CategoryResponseDTO categoryResponseDTO1;
    private CategoryResponseDTO categoryResponseDTO2;
    private List<CategoryResponseDTO> categoryResponseDTOList;
    private CategoryCreateDTO categoryCreateDTO;
    private CategoryUpdateDTO categoryUpdateDTO;

    @BeforeEach
    void setUp() {
        // Setup test data
        categoryResponseDTO1 = new CategoryResponseDTO();
        categoryResponseDTO1.setId(1L);
        categoryResponseDTO1.setName("Electronics");
        categoryResponseDTO1.setDescription("Electronic devices and accessories");
        categoryResponseDTO1.setProductCount(5);
        categoryResponseDTO1.setCreatedAt(LocalDateTime.now());
        categoryResponseDTO1.setUpdatedAt(LocalDateTime.now());

        categoryResponseDTO2 = new CategoryResponseDTO();
        categoryResponseDTO2.setId(2L);
        categoryResponseDTO2.setName("Clothing");
        categoryResponseDTO2.setDescription("Apparel and fashion items");
        categoryResponseDTO2.setProductCount(3);
        categoryResponseDTO2.setCreatedAt(LocalDateTime.now());
        categoryResponseDTO2.setUpdatedAt(LocalDateTime.now());

        categoryResponseDTOList = Arrays.asList(categoryResponseDTO1, categoryResponseDTO2);

        categoryCreateDTO = new CategoryCreateDTO();
        categoryCreateDTO.setName("Books");
        categoryCreateDTO.setDescription("Books and literature");

        categoryUpdateDTO = new CategoryUpdateDTO();
        categoryUpdateDTO.setName("Updated Electronics");
        categoryUpdateDTO.setDescription("Updated electronic devices and accessories");
    }

    @Test
    void getAllCategories() throws Exception {
        when(categoryService.getAllCategories()).thenReturn(categoryResponseDTOList);

        mockMvc.perform(get("/api/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Electronics")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Clothing")));

        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    void getCategoryById() throws Exception {
        when(categoryService.getCategoryById(1L)).thenReturn(categoryResponseDTO1);

        mockMvc.perform(get("/api/categories/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Electronics")));

        verify(categoryService, times(1)).getCategoryById(1L);
    }

    @Test
    void createCategory() throws Exception {
        CategoryResponseDTO createdCategory = new CategoryResponseDTO();
        createdCategory.setId(3L);
        createdCategory.setName("Books");
        createdCategory.setDescription("Books and literature");
        createdCategory.setProductCount(0);
        createdCategory.setCreatedAt(LocalDateTime.now());
        createdCategory.setUpdatedAt(LocalDateTime.now());

        when(categoryService.createCategory(any(CategoryCreateDTO.class))).thenReturn(createdCategory);

        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryCreateDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Books")));

        verify(categoryService, times(1)).createCategory(any(CategoryCreateDTO.class));
    }

    @Test
    void updateCategory() throws Exception {
        CategoryResponseDTO updatedCategory = new CategoryResponseDTO();
        updatedCategory.setId(1L);
        updatedCategory.setName("Updated Electronics");
        updatedCategory.setDescription("Updated electronic devices and accessories");
        updatedCategory.setProductCount(5);
        updatedCategory.setCreatedAt(categoryResponseDTO1.getCreatedAt());
        updatedCategory.setUpdatedAt(LocalDateTime.now());

        when(categoryService.updateCategory(eq(1L), any(CategoryUpdateDTO.class))).thenReturn(updatedCategory);

        mockMvc.perform(put("/api/categories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryUpdateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Updated Electronics")));

        verify(categoryService, times(1)).updateCategory(eq(1L), any(CategoryUpdateDTO.class));
    }

    @Test
    void deleteCategory() throws Exception {
        doNothing().when(categoryService).deleteCategory(1L);

        mockMvc.perform(delete("/api/categories/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(categoryService, times(1)).deleteCategory(1L);
    }
}
