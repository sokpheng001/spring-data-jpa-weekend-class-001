package com.sokpheng.restfulapi001.mapper;

import com.sokpheng.restfulapi001.model.dto.ResponseProductDto;
import com.sokpheng.restfulapi001.model.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface ProductMapstruct {
    ResponseProductDto
    mapFromProductToResponseProductDto(Product product);
}
