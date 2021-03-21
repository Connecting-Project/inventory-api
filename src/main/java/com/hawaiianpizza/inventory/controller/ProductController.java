package com.hawaiianpizza.inventory.controller;

import com.hawaiianpizza.inventory.model.Admin;
import com.hawaiianpizza.inventory.model.Product;
import com.hawaiianpizza.inventory.model.User;
import com.hawaiianpizza.inventory.service.LoginService;
import com.hawaiianpizza.inventory.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/vi/inventory")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    // 전체조회
    @GetMapping(value = "/products")
    public ResponseEntity<?> products() {
        System.out.println("products list");
        try {
            List<Product> list = productService.searchAll();
            return new ResponseEntity<>( list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("login fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    // 제품명 조회
//    @GetMapping(value = "/products")
//    public ResponseEntity<?> productsName(@RequestParam String name) {
//        System.out.println("productsName Controller");
//        try {
//            List<Product> list = productService.searchName(name);
//            return new ResponseEntity<>("login Success", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("login fail", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
    // 카테고리 조회
//    @GetMapping(value = "/products")
//    public ResponseEntity<?> productsCategory(@RequestParam String category) {
//        System.out.println("productsCategory Controller");
//        try {
//            System.out.println("info");
//            return new ResponseEntity<>("login Success", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("login fail", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }

    // S/N 조회
//    @GetMapping(value = "/products")
//    public ResponseEntity<?> productsSN(@RequestParam int sn) {
//        System.out.println("productsSN Controller");
//        try {
//            System.out.println("info");
//            return new ResponseEntity<>("login Success", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("login fail", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
    @PostMapping(value = "/products")
    public ResponseEntity<?> productsUpdate(@RequestBody Product product) {
        System.out.println("productsUpdate Controller");
        try {
            System.out.println("info");
            return new ResponseEntity<>("login Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("login fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping(value = "/products")
    public ResponseEntity<?> productsDelete(@RequestParam String id) {
        System.out.println("productsDelete Controller");
        try {
            System.out.println("info");
            return new ResponseEntity<>("login Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("login fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
