package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;


@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idWarehouse;
    private String codeWarehouse;
    private String nameWarehouse;
    private String address;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Warehouse(int idWarehouse, String codeWarehouse, String nameWarehouse, String address, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.idWarehouse = idWarehouse;
        this.codeWarehouse = codeWarehouse;
        this.nameWarehouse = nameWarehouse;
        this.address = address;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Warehouse() {
    }
    // Getters and setters

    public int getIdWarehouse() {
        return idWarehouse;
    }

    public void setIdWarehouse(int idWarehouse) {
        this.idWarehouse = idWarehouse;
    }

    public String getCodeWarehouse() {
        return codeWarehouse;
    }

    public void setCodeWarehouse(String codeWarehouse) {
        this.codeWarehouse = codeWarehouse;
    }

    public String getNameWarehouse() {
        return nameWarehouse;
    }

    public void setNameWarehouse(String nameWarehouse) {
        this.nameWarehouse = nameWarehouse;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        sb.append("Warehouse ID: ").append(idWarehouse).append("\n");
        sb.append("Warehouse Code: ").append(codeWarehouse).append("\n");
        sb.append("Name: ").append(nameWarehouse).append("\n");
        sb.append("Address: ").append(address).append("\n");
        sb.append("Created Date: ").append(createdDate).append("\n");
        sb.append("Updated Date: ").append(updatedDate).append("\n");
        sb.append("-----------------------------");
        return sb.toString();
    }
}