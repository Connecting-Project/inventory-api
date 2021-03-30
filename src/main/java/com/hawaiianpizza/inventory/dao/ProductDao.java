package com.hawaiianpizza.inventory.dao;

import com.hawaiianpizza.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ProductDao extends JpaRepository<Product, String> {
     Optional<Product> findById(String id);

     List<Product> findByProductNameLike(String s);

    List<Product> findByCategory(String category);

    Product findBySn(String sn);
}
