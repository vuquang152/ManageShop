package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final IProductRepository productRepository;

    @Autowired
    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(int idProduct) {
        productRepository.deleteById(idProduct);
    }

    public Product findProductById(int idProduct) {
        return productRepository.findById(idProduct).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.getAllProducts(pageable);
    }

    // Find products by keyword in their names
    public Page<Product> searchProductsByName(String nameProduct, Pageable pageable) {
        Sort sort = Sort.by(Sort.Direction.ASC, "nameProduct");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return productRepository.searchProductsByName(nameProduct, pageable);
    }

    // Get the top 10 selling products based on quantity sold
    public List<Product> getTopSellingProducts() {
        return productRepository.getTopSellingProducts();
    }
}
