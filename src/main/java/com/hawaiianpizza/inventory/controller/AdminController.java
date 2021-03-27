package com.hawaiianpizza.inventory.controller;

import com.hawaiianpizza.inventory.model.Admin;
import com.hawaiianpizza.inventory.model.AdminLogin;
import com.hawaiianpizza.inventory.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/inventory/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(value = "/admin")
    public ResponseEntity<?> adminCreate(@RequestBody Admin admin) {
        System.out.println("admin create controller");
        try {
            System.out.println(admin);
            Admin ad = adminService.join(admin);
            return new ResponseEntity<>(ad, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("join fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> adminLogin(@RequestBody AdminLogin admin) {
        System.out.println("admin login controller");
        try {
            return new ResponseEntity<>("login Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("login fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/admin")
    public ResponseEntity<?> adminUpdate(@RequestBody Admin admin) {
        System.out.println("admin update controller");
        try {
            return new ResponseEntity<>("login Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("login fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/admin")
    public ResponseEntity<?> adminDelete(@RequestBody String id) {
        System.out.println("admin update controller");
        try {
            return new ResponseEntity<>("login Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("login fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}