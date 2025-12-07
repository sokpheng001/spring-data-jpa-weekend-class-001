package com.sokpheng.restfulapi001.controller;

import com.sokpheng.restfulapi001.model.dto.CreateProductDto;

import com.sokpheng.restfulapi001.model.service.ProductService;
import com.sokpheng.restfulapi001.utils.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;

@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@RestController
public class ProductController {
    private final ProductService productService;
    @PostMapping()
    public ResponseTemplate<Object> createNewProduct(@RequestBody CreateProductDto productDto){
        return ResponseTemplate
                .builder()
                .status(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Created new Product successfully")
                .data(productService.createNewProduct(productDto))
                .build();
    }
}
