package com.sokpheng.restfulapi001.authService;

import com.sokpheng.restfulapi001.model.dto.CreateCustomerDto;
import com.sokpheng.restfulapi001.utils.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;

@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthServiceImpl authService;
    @PostMapping("/register")
    public ResponseTemplate<Object>
    registerUser(@RequestBody CreateCustomerDto createCustomerDto){
        return ResponseTemplate
                .builder()
                .status(HttpStatus.CREATED.toString())
                .date(Date.from(Instant.now()))
                .message("Create new user successfully")
                .data(authService.registerUser(createCustomerDto))
                .build();
    }
    @PutMapping("/reset-password")
    public ResponseTemplate<Object>
    resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){
        return ResponseTemplate
                .builder()
                .status(HttpStatus.CREATED.toString())
                .date(Date.from(Instant.now()))
                .message("Reset user password successfully")
                .data(authService.resetPassword(resetPasswordDto))
                .build();
    }
    @PostMapping("/forget-password")
    public ResponseTemplate<Object>
    forgetPassword(@RequestParam String email){
        return ResponseTemplate
                .builder()
                .status(HttpStatus.CREATED.toString())
                .date(Date.from(Instant.now()))
                .message("Check your email to forget your password")
                .data(authService.forgotPassword(email))
                .build();
    }
}
