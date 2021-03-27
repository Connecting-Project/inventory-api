package com.hawaiianpizza.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "user")
public class User {
    @Id
    private String id;
    private String email;
    private String name;
    private String utoken;

    private int uauth;
    @Column(name = "product_group")
    private String productGroup;
    private String tel;
}
