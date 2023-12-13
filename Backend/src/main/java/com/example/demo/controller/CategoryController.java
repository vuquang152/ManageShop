package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
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
@RequestMapping("/admin/categories")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public void addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
    }

    @PutMapping("/{idCategory}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateCategory(@PathVariable int idCategory, @RequestBody Category category) {
        category.setIdCategory(idCategory);
        categoryService.updateCategory(category);
    }

    @DeleteMapping("/{idCategory}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(@PathVariable int idCategory) {
        categoryService.deleteCategory(idCategory);
    }

    @GetMapping("/{idCategory}")
    public Category getCategory(@PathVariable int idCategory) {
        return categoryService.getCategoryById(idCategory);
    }

    @GetMapping("/all")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping()
    public ResponseEntity<Page<Category>> getAllCategories(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(page-1, pageSize);
        Page<Category> categoryPage = categoryService.getAllCategories(pageable);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(categoryPage, headers, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Category>> searchCategoriesByName(
            @RequestParam String nameCategory,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(page-1, pageSize);
        Page<Category> categoryPage = categoryService.searchCategoriesByName(nameCategory, pageable);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(categoryPage, headers, HttpStatus.OK);
    }
}
