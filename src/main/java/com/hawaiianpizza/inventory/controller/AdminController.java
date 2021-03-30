package com.hawaiianpizza.inventory.controller;

import com.hawaiianpizza.inventory.model.Admin;
import com.hawaiianpizza.inventory.model.AdminLogin;
import com.hawaiianpizza.inventory.model.User;
import com.hawaiianpizza.inventory.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/inventory/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @GetMapping(value = "/id-check")
    public ResponseEntity<?> idCheck(@RequestParam String id) {
        System.out.println("admin create controller");
        try {
            System.out.println(id);
            Admin ad = adminService.idCheck(id);
            if(ad!=null){
                return new ResponseEntity<>("fail", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("sucess", HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("check fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

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
            Admin ad = adminService.login(admin);
            if(ad!=null)
            {
                return new ResponseEntity<>(ad, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("login fail", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("request fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/admin")
    public ResponseEntity<?> adminUpdate(@RequestBody Admin admin) {
        System.out.println("admin update controller");
        try {
            System.out.println(admin);
            Admin ad = adminService.join(admin);
            return new ResponseEntity<>(ad, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("update fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/admin")
    public ResponseEntity<?> adminDelete(@RequestBody String id) {
        System.out.println("admin delete controller");
        try {
            Admin ad = adminService.delete(id);

            if(ad != null){
                return new ResponseEntity<>(ad, HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>("del fail", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("request fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/admin-check")
    public ResponseEntity<?> adminCheck(){
        System.out.println("admin check controller");
        try {
            boolean b = adminService.adminCheck();
            return new ResponseEntity<>(b, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("request fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/user")
    public ResponseEntity<?> userList(){
        System.out.println("User list controller");
        try {
            List<User> ls = adminService.userList();
            if(ls != null){
                return new ResponseEntity<>(ls, HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>("no user", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("request fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/user-search")
    public ResponseEntity<?> userSearch(@RequestParam String name){
        System.out.println("User name search controller");
        try {
            List<User> ls = adminService.userNameSearch("%"+name+"%");
            if(ls != null){
                return new ResponseEntity<>(ls, HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>("no user", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("request fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping(value = "/change-auth")
    public ResponseEntity<?> changeAuth(@RequestBody User user,@RequestParam int level){
        System.out.println("change Auth");
        try {
            User u = adminService.changeAuth(user,level);
            if(u != null){
                return new ResponseEntity<>(u, HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>("no user", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("request fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
