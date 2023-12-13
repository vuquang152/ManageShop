package com.example.demo.service;

import com.example.demo.model.Warehouse;
import com.example.demo.repository.IWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    private final IWarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseService(IWarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Warehouse getWarehouseById(int id) {
        return warehouseRepository.findById(id).orElse(null);
    }

    public Warehouse addWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(Integer id) {
        warehouseRepository.deleteById(id);
    }

    public List<Warehouse> getAllWarehouses(){
        return warehouseRepository.findAll();
    }

    public Page<Warehouse> getAllWarehouses(Pageable pageable) {
        return warehouseRepository.getAllWarehouses(pageable);
    }

    // Find products by keyword in their names
    public Page<Warehouse> searchWarehousesByName(String nameWarehouse, Pageable pageable) {
        Sort sort = Sort.by(Sort.Direction.ASC, "nameWarehouse");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return warehouseRepository.searchWarehousesByName(nameWarehouse, pageable);
    }

}
