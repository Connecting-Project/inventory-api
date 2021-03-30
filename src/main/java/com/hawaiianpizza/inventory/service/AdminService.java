package com.hawaiianpizza.inventory.service;


import com.hawaiianpizza.inventory.dao.AdminDao;
import com.hawaiianpizza.inventory.dao.LoginDao;
import com.hawaiianpizza.inventory.dao.ProductDao;
import com.hawaiianpizza.inventory.model.Admin;
import com.hawaiianpizza.inventory.model.AdminLogin;
import com.hawaiianpizza.inventory.model.Product;
import com.hawaiianpizza.inventory.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private LoginDao loginDao;
    @Autowired
    private ProductDao productDao;
    public List<Admin> test () {
        return adminDao.findAll();
    }

    public Admin join(Admin admin) {
        System.out.println(admin);
        try{
            Admin ret = adminDao.save(admin);
            return ret;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public Admin idCheck(String id) {
        try {
            return adminDao.findById(id).get();
        } catch (Exception e) {
            return null;

        }
    }
    public Admin login(AdminLogin admin) {
        try{
            return  adminDao.findByIdAndPw(admin.getId(),admin.getPwd()).get();
        }
        catch(Exception e){
            return null;
        }

    }

    public Admin delete(String id) {
        try{
            System.out.println(id);
            Admin admin = adminDao.findById(id).get();
            System.out.println(admin);
            adminDao.delete(admin);
            return admin;
        }
        catch (Exception e){
            return null;
        }

    }

    public List<User> userList() {
        return loginDao.findAll();
    }

    public List<User> userNameSearch(String s) {
        return loginDao.findByNameLike(s);
    }

    public User changeAuth(User user,int level) {
        user.setUauth(level);
        return loginDao.save(user);

    }

    public boolean adminCheck() {
        if(loginDao.count()>0)
            return true;
        return false;
    }

    public Product productUserChange(Product product, String uid) {
        User user = loginDao.findById(uid).get();
        product.setUser(user);
        return productDao.save(product);

    }
}