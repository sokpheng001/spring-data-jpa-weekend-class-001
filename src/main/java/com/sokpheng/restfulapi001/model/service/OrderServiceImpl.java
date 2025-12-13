package com.sokpheng.restfulapi001.model.service;

import com.sokpheng.restfulapi001.mapper.OrderMapStruct;
import com.sokpheng.restfulapi001.model.dto.OrderCreateDto;
import com.sokpheng.restfulapi001.model.dto.OrderResponseDto;
import com.sokpheng.restfulapi001.model.entities.Customer;
import com.sokpheng.restfulapi001.model.entities.Order;
import com.sokpheng.restfulapi001.model.entities.OrderStatus;
import com.sokpheng.restfulapi001.model.entities.Product;
import com.sokpheng.restfulapi001.model.repository.CustomerRepository;
import com.sokpheng.restfulapi001.model.repository.OrderRepository;
import com.sokpheng.restfulapi001.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderMapStruct orderMapStruct;

    @Override
    public OrderResponseDto makeAnOrder(OrderCreateDto createDto) {
        Order order = new Order();// the order object for customer
        order.setUuid(UUID.randomUUID().toString());
        order.setOrderName(createDto.orderName());
        order.setOrderedDate(Date.from(Instant.now()));
        order.setIsDeleted(false);
        order.setStatus(OrderStatus.PENDING.toString());
        order.setLocation(createDto.location());
        // set products and customer of the order
        Set<Product> products = new HashSet<>();
        for(String pUuid: createDto.productUuids()){
            Optional<Product> p = productRepository.findProductByUuid(pUuid);
            if(p.isPresent() && p.get().getIsDeleted().equals(true)){
                throw new RuntimeException("Product is deleted");
            }else if(p.isEmpty()){
                throw new RuntimeException("Product is not found");
            }
            products.add(p.get());
        }
        // add products to order
        order.getProducts().addAll(products);
        // start adding customer to order
        Customer customer = customerRepository.findCustomerByUuid(createDto.customerUuid());
        if(customer==null){
            throw new RuntimeException("Customer is not found");
        }
        order.setCustomer(customer);
        // save order to database
        orderRepository.save(order);
        return orderMapStruct.mapFromOrderToOrderResponseDto(order);
    }

    @Override
    public String deleteOrderByUuid(String uuid) {
        Optional<Order> order = orderRepository.findOrderByUuid(uuid);
        if(order.isEmpty()){
            throw new RuntimeException("Order is not found");
        }else if(order.get().getIsDeleted().equals(true)){
            throw new RuntimeException("The order wanted to delete is already deleted");
        }
        order.get().setIsDeleted(true);
        orderRepository.save(order.get());
        return order.get().getUuid();
    }

    @Override
    public Page<OrderResponseDto> getAllOrders(Pageable pageable) {
        Page<Order> orderPage = orderRepository.findAllByIsDeletedIsFalse(pageable);
        return orderPage.map(orderMapStruct::mapFromOrderToOrderResponseDto);
    }

    @Override
    public List<OrderResponseDto> getOrderByCustomerUuid(String customerUuid) {
        Optional<List<Order>> orderList = orderRepository
                .findAllByCustomer_Uuid(customerUuid);
        if(orderList.isEmpty()){
            throw new RuntimeException("No order for this customer");
        }
        return orderList.get().stream().map(
                orderMapStruct::mapFromOrderToOrderResponseDto
        ).toList();
    }

    @Override
    public OrderResponseDto getOrderByUuid(String uuid) {
//        return orderMapStruct.mapFromOrderToOrderResponseDto(
//                orderRepository.findOrderByUuid(uuid).orElseThrow()
//        );
        Optional<Order> order = orderRepository.findOrderByUuid(uuid);
        if(order.isEmpty()){
            throw new RuntimeException("Order is not found");
        }
        return orderMapStruct.mapFromOrderToOrderResponseDto(
                order.get()
        );
    }

    @Override
    public Boolean updateOrderStatus(String status, String orderUuid) {
        if(!status.equals(OrderStatus.PENDING.toString()) ||
                !status.equals(OrderStatus.CANCEL.toString()) ||
                !status.equals(OrderStatus.COMPLETED.toString())){
            throw new RuntimeException("Your status is wrong, you must in three values: [PENDING, CANCEL, COMPLETED]");
        }
        Optional<Order> order = orderRepository.findOrderByUuid(orderUuid);
        if(order.isEmpty()){
            throw new RuntimeException("Order is not found");
        }else if(order.get().getIsDeleted()){
            throw new RuntimeException("Order has been deleted");
        }
        // update status
        order.get().setStatus(status);
        orderRepository.save(order.get());
        return true;
    }
}
