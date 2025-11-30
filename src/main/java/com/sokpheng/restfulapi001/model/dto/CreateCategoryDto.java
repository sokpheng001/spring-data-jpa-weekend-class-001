package com.sokpheng.restfulapi001.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateCategoryDto(
        @NotEmpty(message = "Category name must be not empty")
        @NotNull(message = "Category name must be not null")
        String categoryName
) { }
