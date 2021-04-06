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

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/vi/inventory/devices")
public class DeviceController {


    private final DeviceService deviceService;
    private ProductService productService;
    private LoginService loginService;
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
    @PostMapping(value = "/rent")
    public ResponseEntity<?> DeviceRent(@RequestParam int pro_id, @RequestParam String user_id, @RequestParam int num) {
        System.out.println("productsCreate Controller");
        try {
            Product product = productService.searchId(pro_id);
            User user = loginService.searchId(user_id);
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
            return new ResponseEntity<>("save fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/return")
    public ResponseEntity<?> DeviceReturn(@RequestParam int device_id) {
        System.out.println("productsCreate Controller");
        try {
                Device device = deviceService.searchId(device_id);
                Product pro = device.getProduct();
                pro.setStock(pro.getStock()+device.getQuantity());
                productService.Update(pro);
                deviceService.delete(device.getId());
                productService.Logging(device.getProduct(),device.getUser(),device.getQuantity(),"반납");
                return new ResponseEntity<>(pro, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("save fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
