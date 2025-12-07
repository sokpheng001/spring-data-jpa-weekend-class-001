package com.sokpheng.restfulapi001.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id // primary
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    @Column(length = 200, nullable = false, unique = true)
    private String categoryName;
    private Date createdDate;
    private Boolean isDeleted;
    //
//    @ManyToMany
//    private Set<Product> products = new HashSet<>();
}
