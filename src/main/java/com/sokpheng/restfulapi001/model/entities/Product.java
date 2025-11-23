package com.sokpheng.restfulapi001.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    private String productName;
    private Date createdDate;
    private Date expireDate;
    private Boolean isDeleted;
    @ManyToMany
    @JoinTable(
         name = "categories_products",
         joinColumns = @JoinColumn(name = "product_id"),
         inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();
}
