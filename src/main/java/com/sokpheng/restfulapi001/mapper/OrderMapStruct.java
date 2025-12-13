package com.sokpheng.restfulapi001.mapper;

import com.sokpheng.restfulapi001.model.dto.OrderResponseDto;
import com.sokpheng.restfulapi001.model.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapStruct {
    OrderResponseDto mapFromOrderToOrderResponseDto(
            Order order
    );
}
