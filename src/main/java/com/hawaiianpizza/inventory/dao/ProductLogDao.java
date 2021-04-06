package com.hawaiianpizza.inventory.dao;

import com.hawaiianpizza.inventory.model.Product;
import com.hawaiianpizza.inventory.model.ProductLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ProductLogDao extends JpaRepository<ProductLog, String> {
     Optional<ProductLog> findById(String id);
     List<ProductLog> findByProduct(int id);
}
