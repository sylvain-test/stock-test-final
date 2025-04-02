package com.hopniel.gestionstock.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductUpdateDTO {
    @NotBlank(message = "Le nom du produit est obligatoire")
    private String name;

    @NotBlank(message = "La référence SKU est obligatoire")
    private String sku;

    private String description;

    @NotNull(message = "Le prix est obligatoire")
    @Min(value = 0, message = "Le prix doit être positif")
    private BigDecimal price;
    
    @NotNull(message = "Le prix d'achat est obligatoire")
    @Min(value = 0, message = "Le prix d'achat doit être positif")
    private BigDecimal purchasePrice;
    
    @NotNull(message = "Le prix de vente est obligatoire")
    @Min(value = 0, message = "Le prix de vente doit être positif")
    private BigDecimal sellingPrice;

    @NotNull(message = "La quantité en stock est obligatoire")
    @Min(value = 0, message = "La quantité en stock doit être positive")
    private Integer stockQuantity;
    
    @Min(value = 0, message = "Le niveau minimum de stock doit être positif")
    private Integer minStockLevel;
    
    @Min(value = 0, message = "Le niveau maximum de stock doit être positif")
    private Integer maxStockLevel;

    private Long categoryId;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    public Integer getMinStockLevel() {
        return minStockLevel;
    }

    public void setMinStockLevel(Integer minStockLevel) {
        this.minStockLevel = minStockLevel;
    }

    public Integer getMaxStockLevel() {
        return maxStockLevel;
    }

    public void setMaxStockLevel(Integer maxStockLevel) {
        this.maxStockLevel = maxStockLevel;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
