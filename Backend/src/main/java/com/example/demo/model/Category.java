package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategory;
    private String codeCategory;
    private String nameCategory;
    private String descriptionCategory;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Category(int idCategory, String codeCategory, String nameCategory, String descriptionCategory, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.idCategory = idCategory;
        this.codeCategory = codeCategory;
        this.nameCategory = nameCategory;
        this.descriptionCategory = descriptionCategory;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Category() {

    }

    // Getters and setters

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getCodeCategory() {
        return codeCategory;
    }

    public void setCodeCategory(String codeCategory) {
        this.codeCategory = codeCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getDescriptionCategory() {
        return descriptionCategory;
    }

    public void setDescriptionCategory(String descriptionCategory) {
        this.descriptionCategory = descriptionCategory;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = LocalDateTime.now();
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = LocalDateTime.now();
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
        String sb = "Category ID: " + idCategory + "\n" +
                "Category Code: " + codeCategory + "\n" +
                "Name: " + nameCategory + "\n" +
                "Description: " + descriptionCategory + "\n" +
                "Created Date: " + createdDate + "\n" +
                "Updated Date: " + updatedDate + "\n" +
                "-----------------------------";
        return sb;
    }
}