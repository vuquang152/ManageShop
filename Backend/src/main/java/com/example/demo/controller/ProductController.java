package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/products")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @PutMapping("/{idProduct}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateProduct(@PathVariable int idProduct, @RequestBody Product product) {
        product.setIdProduct(idProduct);
        productService.updateProduct(product);
    }

    @DeleteMapping("/{idProduct}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(@PathVariable int idProduct) {
        productService.deleteProduct(idProduct);
    }

    @GetMapping("/{idProduct}")
    public Product getProductById(@PathVariable int idProduct) {
        return productService.findProductById(idProduct);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping()
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(page-1, pageSize);
        Page<Product> productPage = productService.getAllProducts(pageable);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(productPage, headers, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> searchProductsByName(
            @RequestParam String nameProduct,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(page-1, pageSize);
        Page<Product> productPage = productService.searchProductsByName(nameProduct, pageable);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(productPage, headers, HttpStatus.OK);
    }

    @GetMapping("/top-selling")
    public List<Product> getTopSellingProducts() {
        return productService.getTopSellingProducts();
    }
}
