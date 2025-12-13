package com.sokpheng.restfulapi001.model.repository;

import com.sokpheng.restfulapi001.model.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Integer> {
    Page<Product> findProductByIsDeletedIsFalse(Pageable pageable);
    Optional<Product> findProductByUuid(String uuid);
}
