package com.sokpheng.restfulapi001.model.dto;

import java.util.Date;

public record CategoryResponseDto(
        String uuid,
        String categoryName,
        Date createdDate,
        Boolean isDeleted
) {
}
