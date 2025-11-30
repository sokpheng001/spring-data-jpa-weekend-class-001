package com.sokpheng.restfulapi001.model.dto;

public record CustomerResponseDto(
        String uuid,
        String customerName,
        String email,
        Boolean isDeleted,
        Boolean isVerified
//        ,Set<Order> orders
) { }
