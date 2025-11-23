package com.sokpheng.restfulapi001.model.dto;

import lombok.Builder;

@Builder
public record CreateCustomerDto(
        String customerName,
        String email,
        String password
) { }
