package com.hawaiianpizza.inventory.controller;

import com.hawaiianpizza.inventory.model.GoogleUser;
import com.hawaiianpizza.inventory.model.User;
import com.hawaiianpizza.inventory.service.AdminService;
import com.hawaiianpizza.inventory.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/inventory/accounts")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @PostMapping(value = "/login")
    public ResponseEntity<?> google(@RequestBody GoogleUser guser) {
        System.out.println("google Login Controller");
        try {
            System.out.println(guser);
            System.out.println("info");
            return new ResponseEntity<>( loginService.googleLogin(guser), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("login fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/accounts")
    public ResponseEntity<?> accountsUpdate(@RequestParam String id,@RequestParam String tel) {
        System.out.println("accountsUpdate Controller");
        try {
            User user = loginService.update(id,tel);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("login fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping(value = "")
    public ResponseEntity<?> accountsDelete(@RequestParam String id) {
        System.out.println("accounts Delete Controller");
        try {
            loginService.delete(id);
            return new ResponseEntity<>("delete Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("delete fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
