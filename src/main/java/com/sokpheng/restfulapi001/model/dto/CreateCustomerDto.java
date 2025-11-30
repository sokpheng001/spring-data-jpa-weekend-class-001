package com.sokpheng.restfulapi001.model.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;


@Builder
public record CreateCustomerDto(

        @NotBlank(message = "Customer name must be not blank")
        @NotNull(message = "Customer name must be not null")
        String customerName,
        @Email(message = "Email must be valid")
        String email,
        @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
        message = "Password must be with 8 digits, at least 1 special symbol, 1 lower and  upper case")
        String password
) { }
