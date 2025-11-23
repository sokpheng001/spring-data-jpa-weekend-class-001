package com.sokpheng.restfulapi001.model.dto;

import com.sokpheng.restfulapi001.model.entities.Order;
import java.util.Set;

public record CustomerResponseDto(
        String uuid,
        String customerName,
        String email,
        Boolean isDeleted,
        Boolean isVerified,
        Set<Order> orders
) { }
