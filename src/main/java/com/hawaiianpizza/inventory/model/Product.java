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
    private int id;
    private String category;
    private String buy;
    private String manufacturer;
    private String productname;
    private int price;
    private int quantity;
    private int stock;
    private String sn;
    private String picture;
    private int type;
}
