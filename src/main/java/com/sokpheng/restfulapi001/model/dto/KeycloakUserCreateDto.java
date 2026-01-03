package com.sokpheng.restfulapi001.model.dto;

import lombok.Builder;

@Builder
public record KeycloakUserCreateDto(
        String firstName,
        String lastName,
        String email,
        String password
) { }
