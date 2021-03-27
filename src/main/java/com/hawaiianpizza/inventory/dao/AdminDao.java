package com.hawaiianpizza.inventory.dao;

import com.hawaiianpizza.inventory.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface AdminDao extends JpaRepository<Admin, String> {
     Optional<Admin> findById(String id);

     Optional<Admin> findByIdAndPw(String id, String pwd);

}
