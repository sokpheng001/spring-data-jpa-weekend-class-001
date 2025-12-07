package com.sokpheng.restfulapi001.model.entities;



import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "customers")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    @Column(name = "customer_name", length = 300, nullable = false)
    private String customerName;
    private String email;
    private String password;
    private Boolean isDeleted;
    private Boolean isVerified;
//
//    @OneToMany(mappedBy = "customer",
//            fetch = FetchType.EAGER
//            , cascade = CascadeType.ALL
//            , orphanRemoval = true)
//    private Set<Order> orders = new HashSet<>();
}