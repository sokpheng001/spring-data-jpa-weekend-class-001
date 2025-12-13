package com.sokpheng.restfulapi001.model.dto;

import java.util.Date;
import java.util.Set;

public record OrderResponseDto(
        String uuid,
        String orderName,
        Date orderedDate,
        Boolean isDeleted,
        String location,
        String status,
        Set<ResponseProductDto> products,
        CustomerResponseDto customer
) { }
