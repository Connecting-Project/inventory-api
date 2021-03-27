package com.hawaiianpizza.inventory.service;


import com.hawaiianpizza.inventory.dao.AdminDao;
import com.hawaiianpizza.inventory.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;

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
}