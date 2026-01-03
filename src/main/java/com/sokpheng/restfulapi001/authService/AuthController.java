package com.sokpheng.restfulapi001.authService;

import com.sokpheng.restfulapi001.model.dto.CreateCustomerDto;
import com.sokpheng.restfulapi001.utils.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;

@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
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
}
