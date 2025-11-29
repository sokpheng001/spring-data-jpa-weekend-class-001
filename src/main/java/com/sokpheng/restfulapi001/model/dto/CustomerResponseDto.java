package com.sokpheng.restfulapi001.model.dto;

import com.sokpheng.restfulapi001.model.entities.Order;
import lombok.Builder;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Set;

@Builder
public record CustomerResponseDto(
        String uuid,
        String customerName,
        String email,
        Boolean isDeleted,
        Boolean isVerified
//        ,Set<Order> orders
) { }
