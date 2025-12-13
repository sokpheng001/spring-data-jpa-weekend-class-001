package com.sokpheng.restfulapi001.model.repository;

import com.sokpheng.restfulapi001.model.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository
        extends JpaRepository<Order, Integer> {
    List<Order> findOrderByCustomerId(Integer customerId);
    Optional<Order> findOrderByUuid(String uuid);
    Page<Order> findAllByIsDeletedIsFalse(Pageable pageable);
    Optional<List<Order>> findAllByCustomer_Uuid(String userUuid);
}
