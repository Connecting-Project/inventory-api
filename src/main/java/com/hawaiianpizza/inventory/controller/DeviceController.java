package com.hawaiianpizza.inventory.controller;

import com.hawaiianpizza.inventory.model.Device;
import com.hawaiianpizza.inventory.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/vi/inventory/devices")
public class DeviceController {


    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }
    // 전체조회
    @GetMapping(value = "/list")
    public ResponseEntity<?> devices() {
        System.out.println("devices list");
        try {
            List<Device> list = deviceService.searchAll();
            return new ResponseEntity<>( list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("load fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
