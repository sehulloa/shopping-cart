package com.ulloa.orderservice.service;

import com.ulloa.orderservice.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAllOrders();
    OrderDto getOrderById(Long id);
    OrderDto createOrder(OrderDto orderDto);

}
