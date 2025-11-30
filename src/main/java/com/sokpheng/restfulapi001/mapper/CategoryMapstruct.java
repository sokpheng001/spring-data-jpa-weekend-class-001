package com.sokpheng.restfulapi001.mapper;

import com.sokpheng.restfulapi001.model.dto.CategoryResponseDto;
import com.sokpheng.restfulapi001.model.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapstruct {
    CategoryResponseDto
            mapFromCategoryToCategoryResponseDto(Category category);
}
