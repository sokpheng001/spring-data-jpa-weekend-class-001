package com.sokpheng.restfulapi001.model.service;

import com.sokpheng.restfulapi001.model.dto.OrderCreateDto;
import com.sokpheng.restfulapi001.model.dto.OrderResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    OrderResponseDto makeAnOrder(OrderCreateDto createDto);
    String deleteOrderByUuid(String uuid);
    Page<OrderResponseDto> getAllOrders(Pageable pageable);
    List<OrderResponseDto> getOrderByCustomerUuid(String customerUuid);
    OrderResponseDto getOrderByUuid(String uuid);
    Boolean updateOrderStatus(String status, String orderUuid);
}
