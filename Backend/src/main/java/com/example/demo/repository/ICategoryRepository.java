package com.example.demo.repository;



import com.example.demo.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT c FROM Category c WHERE c.nameCategory LIKE %:nameCategory%")
    Page<Category> searchCategoriesByName(@Param("nameCategory") String nameCategory, Pageable pageable);

    @Query("SELECT c FROM Category c")
    Page<Category> getAllCategories(Pageable pageable);
}
