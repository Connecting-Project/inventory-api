package com.hawaiianpizza.inventory.controller;

import com.hawaiianpizza.inventory.model.Product;
import com.hawaiianpizza.inventory.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/vi/inventory/products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    // 전체조회
    @GetMapping(value = "/list")
    public ResponseEntity<?> products() {
        System.out.println("products list");
        try {
            List<Product> list = productService.searchAll();
            HashMap<String,Object> ret = new HashMap<>();
            ret.put("list_num",list.size());
            ret.put("list",list);
            return new ResponseEntity<>( ret, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("load fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // 제품명 조회
    @GetMapping(value = "/name")
    public ResponseEntity<?> productsName(@RequestParam String name) {
        System.out.println("productsName Controller");
        try {
            List<Product> list = productService.searchName(name);
            HashMap<String,Object> ret = new HashMap<>();
            ret.put("list_num",list.size());
            ret.put("list",list);
            return new ResponseEntity<>( ret, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 카테고리 조회
    @GetMapping(value = "/category")
    public ResponseEntity<?> productsCategory(@RequestParam String category) {
        System.out.println("productsCategory Controller");
        try {
            List<Product> list = productService.searchCategory(category);
            HashMap<String,Object> ret = new HashMap<>();
            ret.put("list_num",list.size());
            ret.put("list",list);
            return new ResponseEntity<>( ret, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("category fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // S/N 조회
    @GetMapping(value = "/sn")
    public ResponseEntity<?> productsSN(@RequestParam String sn) {
        System.out.println("productsSN Controller");
        try {
            Product pro = productService.SearchBySn(sn);
            return new ResponseEntity<>(pro, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
    @PostMapping(value = "/")
    public ResponseEntity<?> productsUpdate(@RequestBody Product product) {
        System.out.println("productsUpdate Controller");
        try {
            System.out.println("update product");
            Product pro = productService.Update(product);
            return new ResponseEntity<>(pro, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("save fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping(value = "/c")
    public ResponseEntity<?> productsCreate(@RequestBody Product product) {
        System.out.println("productsCreate Controller");
        try {
            System.out.println("Create product");
            String sn = product.getCategory();
            int ran = (int) (Math.random()*1000000000);
            sn = sn+ran;
            product.setSn(sn);
            System.out.println(product);
            Product pro = productService.Update(product);
            return new ResponseEntity<>(pro, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("save fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping(value = "/")
    public ResponseEntity<?> productsDelete(@RequestParam String sn) {
        System.out.println("productsDelete Controller");
        try {
            String ret = productService.delete(sn);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("delete fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
