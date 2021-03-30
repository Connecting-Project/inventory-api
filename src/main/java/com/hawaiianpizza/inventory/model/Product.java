package com.hawaiianpizza.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "product")
public class Product {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String category;
    private String buy;
    private String manufacturer;
    @Column(name = "productName")
    private String productName;
    private int price;
    private int quantity;
    private String sn;
    @ManyToOne
    private User user;
}
