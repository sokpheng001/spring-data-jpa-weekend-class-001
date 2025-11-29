package com.sokpheng.restfulapi001.utils;

import lombok.Builder;

import java.util.Date;

@Builder
public record APIErrorResponse(
        String status,
        Date timeStamp,
        String errorMessage
) { }
