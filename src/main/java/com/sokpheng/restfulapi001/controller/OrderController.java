package com.sokpheng.restfulapi001.controller;

import com.sokpheng.restfulapi001.model.dto.OrderCreateDto;
import com.sokpheng.restfulapi001.model.service.OrderService;
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
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public ResponseTemplate<Object> createAnOrder(@RequestBody OrderCreateDto orderCreateDto){
        return ResponseTemplate.builder()
                .status(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Create new order successfully")
                .data(orderService.makeAnOrder(orderCreateDto))
                .build();
    }
    @GetMapping
    public ResponseTemplate<Object> getAllOrders(Pageable pageable){
        return ResponseTemplate.builder()
                .status(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Get All Orders Successfully")
                .data(orderService.getAllOrders(pageable))
                .build();
    }
    @GetMapping("/customer={uuid}")
    public ResponseTemplate<Object> getOrdersByCustomerUuid(@PathVariable String uuid){
        return ResponseTemplate.builder()
                .status(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Get Order by Customer Uuid")
                .data(orderService.getOrderByCustomerUuid(uuid))
                .build();
    }
    @GetMapping("/{uuid}")
    public ResponseTemplate<Object> getOrderByUuid(@PathVariable String uuid){
        return ResponseTemplate.builder()
                .status(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Get Order by Uuid Successfully")
                .data(orderService.getOrderByUuid(uuid))
                .build();
    }
    @PatchMapping("/")
    public ResponseTemplate<Object> updateOrderStatus(@RequestParam(name = "orderUuid")String uuid
            ,@RequestParam(name = "orderStatus") String status ){
        return ResponseTemplate.builder()
                .status(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Updated order status successfully")
                .data(orderService.updateOrderStatus(status, uuid))
                .build();
    }
    @DeleteMapping("/{uuid}")
    public ResponseTemplate<Object> deleteOrderByUuid(@PathVariable String uuid){
        return ResponseTemplate.builder()
                .status(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Deleted Order Successfully")
                .data(orderService.deleteOrderByUuid(uuid))
                .build();
    }
}
