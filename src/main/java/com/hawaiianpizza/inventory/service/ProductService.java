package com.hawaiianpizza.inventory.service;


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
        return productDao.findByProductNameLike("%"+name+"%");
    }

    public List<Product> searchCategory(String category) {
        return productDao.findByCategory(category);
    }

    public Product SearchBySn(String sn) {
        return productDao.findBySn(sn);
    }

    public Product Update(Product product) {
        return productDao.save(product);
    }

    public String delete(String sn) {
        Product product = productDao.findBySn(sn);
        try{
            productDao.delete(product);
            return "delete";
        }
        catch (Exception e){
            System.out.println(e);
            return "fail";
        }
    }
}
