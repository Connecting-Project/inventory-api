package com.hawaiianpizza.inventory.dao;

import com.hawaiianpizza.inventory.model.Device;
import com.hawaiianpizza.inventory.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface DeviceDao extends JpaRepository<Device, String> {

     Optional<Device> findById(int id);
}
