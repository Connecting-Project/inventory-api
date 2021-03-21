package com.hawaiianpizza.inventory.service;


import com.hawaiianpizza.inventory.dao.LoginDao;
import com.hawaiianpizza.inventory.dao.ProductDao;
import com.hawaiianpizza.inventory.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public List<Product> searchAll() {
        return productDao.findAll();
    }

    public List<Product> searchName(String name) {
        return productDao.findByLikeProductName("?"+name+"?");
    }
}