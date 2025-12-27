package com.sokpheng.restfulapi001.model.service;

import com.sokpheng.restfulapi001.exception.CategoryNotFoundException;
import com.sokpheng.restfulapi001.mapper.CategoryMapstruct;
import com.sokpheng.restfulapi001.model.dto.CategoryResponseDto;
import com.sokpheng.restfulapi001.model.dto.CreateCategoryDto;
import com.sokpheng.restfulapi001.model.dto.UpdateCategoryDto;
import com.sokpheng.restfulapi001.model.entities.Category;
import com.sokpheng.restfulapi001.model.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final CategoryMapstruct categoryMapstruct;

    @Override
    public CategoryResponseDto createCategory(CreateCategoryDto createCategoryDto) {
        Category category = new Category();
        category.setUuid(UUID.randomUUID().toString());
        category.setCategoryName(createCategoryDto.categoryName());
        category.setCreatedDate(Date.from(Instant.now()));
        category.setIsDeleted(false);
        // save to database
        categoryRepository.save(category);
        return categoryMapstruct.mapFromCategoryToCategoryResponseDto(
                category
        );
    }

    @Override
    public Page<CategoryResponseDto> getAllCategories(Pageable pageable) {
        Page<Category> categoryPage =
                categoryRepository.findCategoriesByIsDeletedIsFalse(pageable);
        return categoryPage.map(categoryMapstruct::mapFromCategoryToCategoryResponseDto);
    }
    @Override
    public CategoryResponseDto getCategoryByUuid(String uuid) {
        Optional<Category> category = categoryRepository.findCategoryByUuid(uuid);
        if(category.isEmpty()){
            throw new CategoryNotFoundException("Category is not found");
        }
        return categoryMapstruct.mapFromCategoryToCategoryResponseDto(category.get());
    }

    @Override
    public String deleteCategoryByUuid(String uuid) {
        Optional<Category> category = categoryRepository.findCategoryByUuid(uuid);
        if(category.isEmpty()){
            throw new CategoryNotFoundException("Category is not found");
        }
        category.get().setIsDeleted(true);
        categoryRepository.save(category.get());
        return uuid;
    }

    @Override
    public CategoryResponseDto updateCategoryByUuid(String uuid, UpdateCategoryDto updateCategoryDto) {
        Optional<Category> category = categoryRepository.findCategoryByUuid(uuid);
        if(category.isEmpty()){
            throw new CategoryNotFoundException("Category is not found");
        }
        category.get().setCategoryName(updateCategoryDto.categoryName());
        // update
        categoryRepository.save(category.get());
        return categoryMapstruct
                .mapFromCategoryToCategoryResponseDto(category.get());
    }
}
