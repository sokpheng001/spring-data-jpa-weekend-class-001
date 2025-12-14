package com.sokpheng.restfulapi001.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    private String orderName;
    private Date orderedDate;
    //
    private Boolean isDeleted;
    private String location;
    private String status;// COMPLETED, PENDING, CANCELLED

    @ManyToMany
    @JoinTable(
            name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "customer_id")
//    @JsonIgnore
    private Customer customer;
}