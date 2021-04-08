package com.hawaiianpizza.inventory.service;


import com.hawaiianpizza.inventory.dao.DeviceDao;
import com.hawaiianpizza.inventory.dao.LoginDao;
import com.hawaiianpizza.inventory.model.Device;
import com.hawaiianpizza.inventory.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceDao deviceDao;
    @Autowired
    private LoginDao loginDao;

    public List<Device> searchAll() {
        return deviceDao.findAll();
    }

    public Device Update(Device device) {
        return deviceDao.save(device);
    }

    public String delete(int id) {
        Device device = deviceDao.findById(id).get();
        try{
            deviceDao.delete(device);
            return "delete";
        }
        catch (Exception e){
            System.out.println(e);
            return "fail";
        }
    }

    public Device searchId(int device_id) {
        return deviceDao.findById(device_id).get();
    }

    public List<Device> searchUser(String user_id) {
        User user = loginDao.findById(user_id).get();
        return deviceDao.findByUser(user);
    }
}
