package com.sokpheng.restfulapi001.model.service;

import com.sokpheng.restfulapi001.model.dto.CreateCustomerDto;
import com.sokpheng.restfulapi001.model.dto.CustomerResponseDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CustomerService {
    List<CustomerResponseDto> getAllCustomer();
    CustomerResponseDto getCustomerByUuid(String uuid);
    CustomerResponseDto createNewCustomer(CreateCustomerDto createCustomerDto);
    String deleteUserByUuid(String uuid);
}
