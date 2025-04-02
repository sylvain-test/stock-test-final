package com.hopniel.gestionstock.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hopniel.gestionstock.config.TestSecurityConfig;
import com.hopniel.gestionstock.dto.ProductCreateDTO;
import com.hopniel.gestionstock.dto.ProductResponseDTO;
import com.hopniel.gestionstock.dto.ProductUpdateDTO;
import com.hopniel.gestionstock.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
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

@WebMvcTest(ProductController.class)
@Import(TestSecurityConfig.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private ProductResponseDTO productResponseDTO1;
    private ProductResponseDTO productResponseDTO2;
    private List<ProductResponseDTO> productResponseDTOList;
    private ProductCreateDTO productCreateDTO;
    private ProductUpdateDTO productUpdateDTO;

    @BeforeEach
    void setUp() {
        // Setup test data
        productResponseDTO1 = new ProductResponseDTO();
        productResponseDTO1.setId(1L);
        productResponseDTO1.setName("Laptop");
        productResponseDTO1.setSku("LAP-001");
        productResponseDTO1.setDescription("High-performance laptop");
        productResponseDTO1.setPrice(new BigDecimal("999.99"));
        productResponseDTO1.setPurchasePrice(new BigDecimal("800.00"));
        productResponseDTO1.setSellingPrice(new BigDecimal("999.99"));
        productResponseDTO1.setStockQuantity(10);
        productResponseDTO1.setMinStockLevel(5);
        productResponseDTO1.setMaxStockLevel(20);
        productResponseDTO1.setCreatedAt(LocalDateTime.now());
        productResponseDTO1.setUpdatedAt(LocalDateTime.now());

        productResponseDTO2 = new ProductResponseDTO();
        productResponseDTO2.setId(2L);
        productResponseDTO2.setName("Smartphone");
        productResponseDTO2.setSku("PHN-001");
        productResponseDTO2.setDescription("Latest smartphone model");
        productResponseDTO2.setPrice(new BigDecimal("699.99"));
        productResponseDTO2.setPurchasePrice(new BigDecimal("500.00"));
        productResponseDTO2.setSellingPrice(new BigDecimal("699.99"));
        productResponseDTO2.setStockQuantity(15);
        productResponseDTO2.setMinStockLevel(5);
        productResponseDTO2.setMaxStockLevel(30);
        productResponseDTO2.setCreatedAt(LocalDateTime.now());
        productResponseDTO2.setUpdatedAt(LocalDateTime.now());

        productResponseDTOList = Arrays.asList(productResponseDTO1, productResponseDTO2);

        productCreateDTO = new ProductCreateDTO();
        productCreateDTO.setName("Tablet");
        productCreateDTO.setSku("TAB-001");
        productCreateDTO.setDescription("New tablet model");
        productCreateDTO.setPrice(new BigDecimal("499.99"));
        productCreateDTO.setPurchasePrice(new BigDecimal("350.00"));
        productCreateDTO.setSellingPrice(new BigDecimal("499.99"));
        productCreateDTO.setStockQuantity(20);
        productCreateDTO.setMinStockLevel(5);
        productCreateDTO.setMaxStockLevel(40);

        productUpdateDTO = new ProductUpdateDTO();
        productUpdateDTO.setName("Updated Laptop");
        productUpdateDTO.setSku("LAP-001");
        productUpdateDTO.setDescription("Updated high-performance laptop");
        productUpdateDTO.setPrice(new BigDecimal("1099.99"));
        productUpdateDTO.setPurchasePrice(new BigDecimal("900.00"));
        productUpdateDTO.setSellingPrice(new BigDecimal("1099.99"));
        productUpdateDTO.setStockQuantity(15);
        productUpdateDTO.setMinStockLevel(5);
        productUpdateDTO.setMaxStockLevel(30);
    }

    @Test
    void whenGetAllProducts_thenReturnProductsList() throws Exception {
        when(productService.getAllProducts()).thenReturn(productResponseDTOList);

        mockMvc.perform(get("/api/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Laptop")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Smartphone")));

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void whenGetProductById_thenReturnProduct() throws Exception {
        when(productService.getProductById(1L)).thenReturn(productResponseDTO1);

        mockMvc.perform(get("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Laptop")));

        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    void whenGetProductBySku_thenReturnProduct() throws Exception {
        when(productService.getProductBySku("LAP-001")).thenReturn(productResponseDTO1);

        mockMvc.perform(get("/api/products/sku/LAP-001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Laptop")));

        verify(productService, times(1)).getProductBySku("LAP-001");
    }

    @Test
    void whenCreateProduct_thenReturnCreatedProduct() throws Exception {
        ProductResponseDTO createdProduct = new ProductResponseDTO();
        createdProduct.setId(3L);
        createdProduct.setName("Tablet");
        createdProduct.setSku("TAB-001");
        createdProduct.setDescription("New tablet model");
        createdProduct.setPrice(new BigDecimal("499.99"));
        createdProduct.setPurchasePrice(new BigDecimal("350.00"));
        createdProduct.setSellingPrice(new BigDecimal("499.99"));
        createdProduct.setStockQuantity(20);
        createdProduct.setMinStockLevel(5);
        createdProduct.setMaxStockLevel(40);
        createdProduct.setCreatedAt(LocalDateTime.now());
        createdProduct.setUpdatedAt(LocalDateTime.now());

        when(productService.createProduct(any(ProductCreateDTO.class))).thenReturn(createdProduct);

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productCreateDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Tablet")));

        verify(productService, times(1)).createProduct(any(ProductCreateDTO.class));
    }

    @Test
    void whenUpdateProduct_thenReturnUpdatedProduct() throws Exception {
        ProductResponseDTO updatedProduct = new ProductResponseDTO();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Laptop");
        updatedProduct.setSku("LAP-001");
        updatedProduct.setDescription("Updated high-performance laptop");
        updatedProduct.setPrice(new BigDecimal("1099.99"));
        updatedProduct.setPurchasePrice(new BigDecimal("900.00"));
        updatedProduct.setSellingPrice(new BigDecimal("1099.99"));
        updatedProduct.setStockQuantity(15);
        updatedProduct.setMinStockLevel(5);
        updatedProduct.setMaxStockLevel(30);
        updatedProduct.setCreatedAt(productResponseDTO1.getCreatedAt());
        updatedProduct.setUpdatedAt(LocalDateTime.now());

        when(productService.updateProduct(eq(1L), any(ProductUpdateDTO.class))).thenReturn(updatedProduct);

        mockMvc.perform(put("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productUpdateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Updated Laptop")));

        verify(productService, times(1)).updateProduct(eq(1L), any(ProductUpdateDTO.class));
    }

    @Test
    void whenUpdateProductStock_thenReturnUpdatedProduct() throws Exception {
        ProductResponseDTO updatedProduct = new ProductResponseDTO();
        updatedProduct.setId(1L);
        updatedProduct.setName("Laptop");
        updatedProduct.setSku("LAP-001");
        updatedProduct.setDescription("High-performance laptop");
        updatedProduct.setPrice(new BigDecimal("999.99"));
        updatedProduct.setPurchasePrice(new BigDecimal("800.00"));
        updatedProduct.setSellingPrice(new BigDecimal("999.99"));
        updatedProduct.setStockQuantity(20);
        updatedProduct.setMinStockLevel(5);
        updatedProduct.setMaxStockLevel(20);
        updatedProduct.setCreatedAt(productResponseDTO1.getCreatedAt());
        updatedProduct.setUpdatedAt(LocalDateTime.now());

        when(productService.updateStock(eq(1L), eq(20))).thenReturn(updatedProduct);

        mockMvc.perform(patch("/api/products/1/stock")
                .contentType(MediaType.APPLICATION_JSON)
                .param("quantity", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.stockQuantity", is(20)));

        verify(productService, times(1)).updateStock(eq(1L), eq(20));
    }

    @Test
    void whenDeleteProduct_thenReturnNoContent() throws Exception {
        doNothing().when(productService).deleteProduct(1L);

        mockMvc.perform(delete("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).deleteProduct(1L);
    }

    @Test
    void whenGetLowStockProducts_thenReturnLowStockProductsList() throws Exception {
        when(productService.getLowStockProducts(10)).thenReturn(Arrays.asList(productResponseDTO1));

        mockMvc.perform(get("/api/products/low-stock")
                .param("threshold", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Laptop")));

        verify(productService, times(1)).getLowStockProducts(10);
    }
}
