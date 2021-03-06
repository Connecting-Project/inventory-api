package com.hawaiianpizza.inventory.controller;

import com.hawaiianpizza.inventory.model.Product;
import com.hawaiianpizza.inventory.model.ProductLog;
import com.hawaiianpizza.inventory.model.Products;
import com.hawaiianpizza.inventory.model.User;
import com.hawaiianpizza.inventory.service.LoginService;
import com.hawaiianpizza.inventory.service.ProductService;
import org.apache.tomcat.jni.Time;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/vi/inventory/products")
public class ProductController {


    private final ProductService productService;
    private final LoginService loginService;
    public ProductController(ProductService productService,LoginService loginService) {
        this.loginService = loginService;
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
            List<Product> list = new ArrayList<>();
            list.add(pro);
            return new ResponseEntity<>(list, HttpStatus.OK);
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
            HashMap<String, String> category = new HashMap<>();

            category.put("컴퓨터","CPT");
            category.put("서버","SRV");
            category.put("네트워크","NET");
            category.put("주변기기","DEV");
            category.put("센서","SEN");
            category.put("전기부품","ELE");
            category.put("엑추에이터","ACT");
            category.put("오픈소스H/W","OPS");
            category.put("기타","ETC");
            String sn = category.get(product.getCategory());
            int ran = (int) (Math.random()*1000000);
            sn = sn+ran;
            product.setSn(sn);
            productService.Update(product);
            Product pro = productService.SearchBySn(product.getSn());

            if(product.getType()==1){
                productsCraete(pro);

            }
            return new ResponseEntity<>(pro, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("save fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    private void productsCraete(Product pro) {
        try {
            for(int i = 1;i<=pro.getQuantity();i++){
                Products products = new Products();
                Product temp = productService.searchId(pro.getId());
                System.out.println(temp);
                products.setProduct(temp);
                products.setSn(pro.getSn()+i);
                User u = loginService.searchId("1");
                products.setUser(u);
                System.out.println(products);
                productService.productsSave(products);
            }
        }catch (Exception e){
            System.out.println(e);
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

    //상세 정보
    @GetMapping(value = "/detail")
    public ResponseEntity<?> productDetail(@RequestParam int id) {
        System.out.println("productsDelete Controller");
        try {
            Product product = productService.searchId(id);
            System.out.println(product);
            List<ProductLog> logs = productService.LogList(product);

            System.out.println(logs);
            HashMap<String,Object> ret = new HashMap<>();
            ret.put("product",product);
            ret.put("logs",logs);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("detail fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
