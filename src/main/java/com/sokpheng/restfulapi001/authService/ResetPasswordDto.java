package com.sokpheng.restfulapi001.authService;

public record ResetPasswordDto(
        String email,
        String userId,
        String newPassword,
        String confirmPassword
) { }
