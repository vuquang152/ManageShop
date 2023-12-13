package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduct;
    private String codeProduct;
    private String nameProduct;
    private BigDecimal price;
    private String descriptionProduct;
    private String image;
    private int quantityInStock;
    private int quantitySold;
    private int idStore;
    private int idCategory;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Product(int idProduct, String codeProduct, String nameProduct, BigDecimal price, String descriptionProduct, String image, int quantityInStock, int quantitySold, int idStore, int idCategory, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.idProduct = idProduct;
        this.codeProduct = codeProduct;
        this.nameProduct = nameProduct;
        this.price = price;
        this.descriptionProduct = descriptionProduct;
        this.image = image;
        this.quantityInStock = quantityInStock;
        this.quantitySold = quantitySold;
        this.idStore = idStore;
        this.idCategory = idCategory;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Product() {

    }

    // Getters and setters

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getCodeProduct() {
        return codeProduct;
    }

    public void setCodeProduct(String codeProduct) {
        this.codeProduct = codeProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public int getIdStore() {
        return idStore;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
        updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        LocalDateTime originalCreatedDate = createdDate;
        createdDate = LocalDateTime.now();

        if (originalCreatedDate != null) {
            createdDate = originalCreatedDate;
        }

        updatedDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product ID: ").append(idProduct).append("\n");
        sb.append("Product Code: ").append(codeProduct).append("\n");
        sb.append("Name: ").append(nameProduct).append("\n");
        sb.append("Price: ").append(price).append("\n");
        sb.append("Description: ").append(descriptionProduct).append("\n");
        sb.append("Image: ").append(image).append("\n");
        sb.append("Quantity in Stock: ").append(quantityInStock).append("\n");
        sb.append("Quantity Sold: ").append(quantitySold).append("\n");
        sb.append("Store ID: ").append(idStore).append("\n");
        sb.append("Category ID: ").append(idCategory).append("\n");
        sb.append("Created Date: ").append(createdDate).append("\n");
        sb.append("Updated Date: ").append(updatedDate).append("\n");
        sb.append("-----------------------------");
        return sb.toString();
    }
}