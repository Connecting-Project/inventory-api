package com.hawaiianpizza.inventory.controller;

import com.hawaiianpizza.inventory.model.Admin;
import com.hawaiianpizza.inventory.model.User;
import com.hawaiianpizza.inventory.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/inventory/")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @PostMapping(value = "/login")
    public ResponseEntity<?> google(@RequestBody User user) {
        System.out.println("google Login Controller");
        try {
            System.out.println(user);
            System.out.println("info");
            loginService.googleLogin(user);
            return new ResponseEntity<>("login Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("login fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping(value = "/accounts")
    public ResponseEntity<?> accounts(@RequestParam String id, String email) {
        System.out.println("accounts Controller");
        try {
            System.out.println("info");
            return new ResponseEntity<>("login Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("login fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping(value = "/accounts")
    public ResponseEntity<?> accountsUpdate(@RequestParam String email) {
        System.out.println("accountsUpdate Controller");
        try {
            System.out.println("info");
            return new ResponseEntity<>("login Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("login fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping(value = "/accounts")
    public ResponseEntity<?> accountsDelete(@RequestParam String id) {
        System.out.println("accountsDelete Controller");
        try {
            System.out.println("info");
            return new ResponseEntity<>("login Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("login fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
