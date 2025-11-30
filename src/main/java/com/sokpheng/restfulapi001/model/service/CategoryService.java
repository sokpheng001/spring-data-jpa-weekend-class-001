package com.sokpheng.restfulapi001.model.service;

import com.sokpheng.restfulapi001.model.dto.CategoryResponseDto;
import com.sokpheng.restfulapi001.model.dto.CreateCategoryDto;
import com.sokpheng.restfulapi001.model.dto.UpdateCategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryResponseDto createCategory(CreateCategoryDto createCategoryDto);
    Page<CategoryResponseDto> getAllCategories(Pageable pageable);
    CategoryResponseDto getCategoryByUuid(String uuid);
    String deleteCategoryByUuid(String uuid);
    CategoryResponseDto updateCategoryByUuid(String uuid, UpdateCategoryDto updateCategoryDto);
}
