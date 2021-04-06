package com.hawaiianpizza.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.DateTimeException;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "productlog")
public class ProductLog {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
    private String status;
    private LocalDateTime timestamp;
}
