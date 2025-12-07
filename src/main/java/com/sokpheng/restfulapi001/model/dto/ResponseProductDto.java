package com.sokpheng.restfulapi001.model.dto;

import java.util.Date;
import java.util.Set;

public record ResponseProductDto(
        String uuid,
        String productName,
        Date createdDate,
        Date expireDate,
        Boolean isDeleted,
        Set<CategoryResponseDto> categories
) { }
