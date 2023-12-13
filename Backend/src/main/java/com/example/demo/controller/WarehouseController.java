package com.example.demo.controller;

import com.example.demo.model.Warehouse;
import com.example.demo.service.WarehouseService;
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
@RequestMapping("/admin/warehouses")
@CrossOrigin(origins = "*", maxAge = 3600)
public class WarehouseController {
    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public void addWarehouse(@RequestBody Warehouse warehouse) {
        warehouseService.addWarehouse(warehouse);
    }

    @PutMapping("/{idWarehouse}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateWarehouse(@PathVariable int idWarehouse, @RequestBody Warehouse warehouse) {
        warehouse.setIdWarehouse(idWarehouse);
        warehouseService.updateWarehouse(warehouse);
    }

    @DeleteMapping("/{idWarehouse}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteWarehouse(@PathVariable int idWarehouse) {
        warehouseService.deleteWarehouse(idWarehouse);
    }

    @GetMapping("/{idWarehouse}")
    public Warehouse getWarehouse(@PathVariable int idWarehouse) {
        return warehouseService.getWarehouseById(idWarehouse);
    }

    @GetMapping("/all")
    public List<Warehouse> getAllWarehouses(){
        return warehouseService.getAllWarehouses();
    }

    @GetMapping()
    public ResponseEntity<Page<Warehouse>> getAllWarehouses(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(page-1, pageSize);
        Page<Warehouse> warehousePage = warehouseService.getAllWarehouses(pageable);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(warehousePage, headers, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Warehouse>> searchWarehousesByName(
            @RequestParam String nameWarehouse,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(page-1, pageSize);
        Page<Warehouse> warehousePage = warehouseService.searchWarehousesByName(nameWarehouse, pageable);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(warehousePage, headers, HttpStatus.OK);
    }
}
