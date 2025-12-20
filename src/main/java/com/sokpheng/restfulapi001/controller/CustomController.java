
package com.sokpheng.restfulapi001.controller;

import com.sokpheng.restfulapi001.model.dto.CreateCustomerDto;
import com.sokpheng.restfulapi001.model.service.CustomerService;
import com.sokpheng.restfulapi001.utils.ResponseTemplate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomController {
    private final CustomerService customerService;

    @GetMapping("/paginate")
    public ResponseTemplate<Object>
    getAllCustomersByPaginate(Pageable pageable){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .status(HttpStatus.OK.toString())
                .message("Get All Customers by Pagination Successfully")
                .data(customerService.getAllCustomersByPagination(pageable))
                .build();
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseTemplate<Object> getAllCustomers(){
        return ResponseTemplate
                .builder()
                .date(Date.from(Instant.now()))
                .status(HttpStatus.OK.toString())
                .message("Get All Customers Successfully")
                .data(customerService.getAllCustomer())
                .build();
    }
    @GetMapping("/{uuid}")
    public ResponseTemplate<Object> getCustomerByUuid(@PathVariable String uuid){
        return ResponseTemplate
                .builder()
                .status(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Get Customer by uuid successfully")
                .data(customerService.getCustomerByUuid(uuid))
                .build();
    }
    @PostMapping
    public ResponseTemplate<Object> createNewCustomer
            (@RequestBody @Valid CreateCustomerDto createCustomerDto){
        return ResponseTemplate
                .builder()
                .status(HttpStatus.CREATED.toString())
                .date(Date.from(Instant.now()))
                .message("Create customer successfully")
                .data(customerService.createNewCustomer(createCustomerDto))
                .build();
    }
    @DeleteMapping("/{uuid}")
    public ResponseTemplate<Object> deleteCustomerByUuid(@PathVariable String uuid){
        return ResponseTemplate
                .builder()
                .status(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Delete customer successfully")
                .data(customerService.deleteUserByUuid(uuid))
                .build();
    }
}
