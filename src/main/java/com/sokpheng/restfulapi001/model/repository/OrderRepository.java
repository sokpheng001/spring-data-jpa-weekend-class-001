package com.sokpheng.restfulapi001.model.repository;

import com.sokpheng.restfulapi001.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
