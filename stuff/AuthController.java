//package com.sokpheng.restfulapi001.security;
//
//import com.sokpheng.restfulapi001.model.dto.CreateCustomerDto;
//import com.sokpheng.restfulapi001.utils.ResponseTemplate;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.Instant;
//import java.util.Date;
//
//@RestController
//@RequestMapping("/api/v1/auth")
//@RequiredArgsConstructor
//public class AuthController {
//    private final AuthService authService;
//    @PostMapping("/create")
//    public ResponseTemplate<Object> createNewCustomer
//            (@RequestBody @Valid CreateCustomerDto createCustomerDto){
//        return ResponseTemplate
//                .builder()
//                .status(HttpStatus.CREATED.toString())
//                .date(Date.from(Instant.now()))
//                .message("Registered a customer successfully")
//                .data(authService.register(createCustomerDto))
//                .build();
//    }
//}
