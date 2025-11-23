package com.sokpheng.restfulapi001.mapper;

import com.sokpheng.restfulapi001.model.dto.CustomerResponseDto;
import com.sokpheng.restfulapi001.model.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapstruct {
    CustomerResponseDto
    mapFromCustomerToCustomerResponseDto(Customer customer);
}
