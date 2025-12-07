package com.sokpheng.restfulapi001.model.service;


import com.sokpheng.restfulapi001.model.dto.CreateProductDto;
import com.sokpheng.restfulapi001.model.dto.ResponseProductDto;
import com.sokpheng.restfulapi001.model.dto.UpdateProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ResponseProductDto createNewProduct(CreateProductDto createProductDto);
    Page<ResponseProductDto> getAllProducts(Pageable pageable);
    ResponseProductDto updateProductByUuid(String uuid, UpdateProductDto updateProductDto);
    String deleteProductByUuid(String uuid);
}
