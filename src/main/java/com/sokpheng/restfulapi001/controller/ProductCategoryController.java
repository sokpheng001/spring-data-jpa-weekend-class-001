package com.sokpheng.restfulapi001.controller;
import com.sokpheng.restfulapi001.model.dto.CreateCategoryDto;
import com.sokpheng.restfulapi001.model.dto.UpdateCategoryDto;
import com.sokpheng.restfulapi001.model.service.CategoryService;
import com.sokpheng.restfulapi001.utils.ResponseTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class ProductCategoryController {
    private final CategoryService categoryService;
    @GetMapping()
    public ResponseTemplate<Object> getAllProductCategories(Pageable pageable){
        return ResponseTemplate
                .builder()
                .status(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Get All Categories successfully")
                .data(categoryService.getAllCategories(pageable))
                .build();
    }
    @PostMapping()
    public ResponseTemplate<Object> createProductCategory(@RequestBody CreateCategoryDto createCategoryDto){
        return ResponseTemplate
                .builder()
                .status(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("New Product Category has been created successfully")
                .data(categoryService.createCategory(createCategoryDto))
                .build();
    }
    @DeleteMapping("/{uuid}")
    public ResponseTemplate<Object> deleteProductCategoryByUuid(@PathVariable(name = "uuid") String uuid){
        return ResponseTemplate
                .builder()
                .status(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Deleted product category successfully")
                .data(categoryService.deleteCategoryByUuid(uuid))
                .build();
    }
    @PatchMapping("/{uuid}")
    public ResponseTemplate<Object> updateProductCategory(@PathVariable String uuid,
                                                          @RequestBody UpdateCategoryDto updateCategoryDto){
        return ResponseTemplate
                .builder()
                .status(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Updated product category successfully")
                .data(categoryService.updateCategoryByUuid(uuid, updateCategoryDto))
                .build();
    }
}
