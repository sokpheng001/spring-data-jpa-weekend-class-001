package com.sokpheng.restfulapi001.model.dto;

import java.util.Set;

public record OrderCreateDto(
        String orderName,
        String location,
        Set<String> productUuids,
        String customerUuid
) { }
