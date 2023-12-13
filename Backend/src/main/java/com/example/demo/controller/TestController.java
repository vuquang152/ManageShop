package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('SALER') or hasRole('ADMIN') or hasRole('WAREHOUSESTAFF') or hasRole('CUSTOMERSERVICE')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/saler")
    @PreAuthorize("hasRole('SALER') or hasRole('ADMIN')")
    public String salerAccess() {
        return "SALER Board.";
    }

    @GetMapping("/customer-service")
    @PreAuthorize("hasRole('CUSTOMERSERVICE') or hasRole('ADMIN')")
    public String customerServiceAccess() {
        return "CUSTOMERSERVICE Board.";
    }

    @GetMapping("/warehouse-staff")
    @PreAuthorize("hasRole('WAREHOUSESTAFF') or hasRole('ADMIN')")
    public String warehouseStaffAccess() {
        return "WAREHOUSESTAFF Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}