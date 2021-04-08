package com.hawaiianpizza.inventory.controller;

import com.hawaiianpizza.inventory.model.Device;
import com.hawaiianpizza.inventory.model.Product;
import com.hawaiianpizza.inventory.model.User;
import com.hawaiianpizza.inventory.service.DeviceService;
import com.hawaiianpizza.inventory.service.LoginService;
import com.hawaiianpizza.inventory.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/vi/inventory/devices")
public class DeviceController {


    private final DeviceService deviceService;
    private final ProductService productService;
    private final LoginService loginService;
    public DeviceController(DeviceService deviceService,ProductService productService,LoginService loginService) {
        this.deviceService = deviceService;
        this.productService = productService;
        this.loginService = loginService;
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
    @PostMapping(value = "/rent")
    public ResponseEntity<?> DeviceRent(@RequestParam int pro_id, @RequestParam String user_id, @RequestParam int num) {
        System.out.println("Device Rent Controller");
        try {
            System.out.println(pro_id);
            Product product = productService.searchId(pro_id);
            System.out.println(product);
            User user = loginService.searchId(user_id);
            System.out.println(user);
            if(product.getStock()>=num){
                product.setStock(product.getStock()-num);
                Product pro = productService.Update(product);
                Device device = new Device();
                device.setProduct(product);
                device.setUser(user);
                device.setQuantity(num);
                deviceService.Update(device);
                productService.Logging(product,user,num,"대여");
                return new ResponseEntity<>(device, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("재고부족", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Rent fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/return")
    public ResponseEntity<?> DeviceReturn(@RequestParam int device_id) {
        System.out.println("DeviceReturn Controller");
        try {
                Device device = deviceService.searchId(device_id);
                Product pro = device.getProduct();
                pro.setStock(pro.getStock()+device.getQuantity());
                productService.Update(pro);
                deviceService.delete(device.getId());
                productService.Logging(device.getProduct(),device.getUser(),device.getQuantity(),"반납");
                return new ResponseEntity<>(pro, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("return fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/user-device")
    public ResponseEntity<?> user(@RequestParam String user_id) {
        System.out.println("user device Controller");
        try {
            List<Device> list = deviceService.searchUser(user_id);
            HashMap<String,Object> ret = new HashMap<>();
            ret.put("list",list);
            ret.put("size",list.size());
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("user device list fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
