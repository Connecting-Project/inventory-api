package com.hawaiianpizza.inventory.service;


import com.hawaiianpizza.inventory.dao.DeviceDao;
import com.hawaiianpizza.inventory.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceDao deviceDao;

    public List<Device> searchAll() {
        return deviceDao.findAll();
    }


//
//    public List<Device> searchName(String name) {
//        return deviceDao.findByDeviceNameLike("%"+name+"%");
//    }
//
//    public List<Device> searchCategory(String category) {
//        return deviceDao.findByCategory(category);
//    }
//
//    public Device SearchBySn(String sn) {
//        return deviceDao.findBySn(sn);
//    }
//
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
}
