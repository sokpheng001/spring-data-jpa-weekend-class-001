package com.sokpheng.restfulapi001.controller;

import com.sokpheng.restfulapi001.model.dto.CreateProductDto;

import com.sokpheng.restfulapi001.model.dto.UpdateProductDto;
import com.sokpheng.restfulapi001.model.service.ProductService;
import com.sokpheng.restfulapi001.utils.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping()
    public ResponseTemplate<Object> getAllProductsByPaginate(Pageable pageable){
        return ResponseTemplate
                .builder()
                .status(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Get All Products Successfully")
                .data(productService.getAllProducts(pageable))
                .build();
    }
    @PatchMapping("/{uuid}")
    public ResponseTemplate<Object> updateProductByUuid(@PathVariable String uuid
            , @RequestBody UpdateProductDto updateProductDto){
        return ResponseTemplate
                .builder()
                .status(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Updated Product successfully")
                .data(productService.updateProductByUuid(uuid, updateProductDto))
                .build();
    }
    @DeleteMapping("/{uuid}")
    public ResponseTemplate<Object> deleteProductByUuid(@PathVariable String uuid){
        return ResponseTemplate
                .builder()
                .status(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Deleted Product successfully")
                .data(productService.deleteProductByUuid(uuid))
                .build();
    }
}
