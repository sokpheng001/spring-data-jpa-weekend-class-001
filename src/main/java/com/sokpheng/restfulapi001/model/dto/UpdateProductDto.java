package com.sokpheng.restfulapi001.model.dto;

import java.time.LocalDate;

public record UpdateProductDto(
        String productName,
        LocalDate expireDate
) { }
