package com.hawaiianpizza.inventory.service;


import com.hawaiianpizza.inventory.dao.ProductDao;
import com.hawaiianpizza.inventory.dao.ProductLogDao;
import com.hawaiianpizza.inventory.model.Product;
import com.hawaiianpizza.inventory.model.ProductLog;
import com.hawaiianpizza.inventory.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductLogDao productLogDao;
    public List<Product> searchAll() {
        return productDao.findAll();
    }

    public List<Product> searchName(String name) {
        return productDao.findByProductnameLike("%"+name+"%");
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
    public ProductLog Logging(Product product, User user, int num, String status){
        ProductLog productLog = new ProductLog();
        productLog.setProduct(product);
        productLog.setQuantity(num);
        productLog.setUser(user);
        return productLogDao.save(productLog);
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

    public Product searchId(int id) {
        return productDao.findById(id).get();
    }

    public List<ProductLog> LogList(Product product) {
        return productLogDao.findByProduct(product);
    }
}
