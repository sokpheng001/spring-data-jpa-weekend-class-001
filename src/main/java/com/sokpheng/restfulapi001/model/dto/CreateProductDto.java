package com.sokpheng.restfulapi001.model.dto;

import java.time.LocalDate;

import java.util.Set;

public record CreateProductDto(
        String productName,
        LocalDate expireDate,
        Set<String> categoriesNames
) { }
