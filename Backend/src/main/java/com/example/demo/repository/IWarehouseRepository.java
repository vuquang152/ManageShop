package com.example.demo.repository;

import com.example.demo.model.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IWarehouseRepository extends JpaRepository<Warehouse, Integer> {
    @Query("SELECT w FROM Warehouse w WHERE w.nameWarehouse LIKE %:nameWarehouse%")
    Page<Warehouse> searchWarehousesByName(@Param("nameWarehouse") String nameWarehouse, Pageable pageable);

    @Query("SELECT w FROM Warehouse w")
    Page<Warehouse> getAllWarehouses(Pageable pageable);

}
