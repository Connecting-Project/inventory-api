package com.hawaiianpizza.inventory.dao;

import com.hawaiianpizza.inventory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface LoginDao extends JpaRepository<User, String> {
     Optional<User> findById(String id);

    List<User> findByUauth(int i);
}
