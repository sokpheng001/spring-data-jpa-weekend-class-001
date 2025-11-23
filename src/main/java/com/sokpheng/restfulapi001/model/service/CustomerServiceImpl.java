package com.sokpheng.restfulapi001.model.service;

import com.sokpheng.restfulapi001.mapper.CustomerMapstruct;
import com.sokpheng.restfulapi001.model.dto.CreateCustomerDto;
import com.sokpheng.restfulapi001.model.dto.CustomerResponseDto;
import com.sokpheng.restfulapi001.model.entities.Customer;
import com.sokpheng.restfulapi001.model.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final CustomerMapstruct customerMapstruct;
    @Override
    public  List<CustomerResponseDto> getAllCustomer() {
        // logic to map object here
        List<Customer> customers = customerRepository.findAll();// from database
        List<CustomerResponseDto> customerResponseDtos
                 = new ArrayList<>();
        //
        for(Customer customer: customers){
            CustomerResponseDto customerResponseDto =
                    customerMapstruct
                            .mapFromCustomerToCustomerResponseDto(customer);
            customerResponseDtos.add(customerResponseDto);
        }
        return customerResponseDtos;
    }

    @Override
    public CustomerResponseDto getCustomerByUuid(String uuid) {
        // logic to map object here
        return customerMapstruct.mapFromCustomerToCustomerResponseDto(
                customerRepository.findCustomerByUuid(uuid)
        );
    }

    @Override
    public CustomerResponseDto createNewCustomer(CreateCustomerDto createCustomerDto) {
        // logic to save customer
        Customer customer = new Customer();
        customer.setUuid(UUID.randomUUID().toString());
        customer.setCustomerName(createCustomerDto.customerName());
        customer.setEmail(createCustomerDto.email());
        customer.setPassword(createCustomerDto.password());
        customer.setIsDeleted(false);
        customer.setIsVerified(true);
        // save to database
        customerRepository.save(customer);
        return customerMapstruct.mapFromCustomerToCustomerResponseDto(
                customer
        );
    }

    @Override
    public String deleteUserByUuid(String uuid) {
        Optional<Customer> customer =
                Optional.ofNullable(customerRepository.findCustomerByUuid(uuid));
        if(customer.isPresent()){
            customerRepository.delete(customer.get());
            return uuid;
        }
        return null;
    }
}
