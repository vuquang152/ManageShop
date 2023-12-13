package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.nameProduct LIKE %:nameProduct%")
    Page<Product> searchProductsByName(String nameProduct, Pageable pageable);

    @Query("SELECT p FROM Product p")
    Page<Product> getAllProducts(Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.quantitySold DESC")
    List<Product> getTopSellingProducts();
}
