package com.sokpheng.restfulapi001.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id // primary
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    @Column(length = 200, nullable = false)
    private String categoryName;
    private Date createdDate;
    @Column(insertable = false)
    private Boolean isDeleted;
    //
//    @ManyToMany
//    private Set<Product> products = new HashSet<>();
}
