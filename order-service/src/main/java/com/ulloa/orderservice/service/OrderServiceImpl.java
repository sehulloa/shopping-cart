package com.ulloa.orderservice.service;

import com.ulloa.orderservice.dto.OrderDetailDto;
import com.ulloa.orderservice.dto.OrderDto;
import com.ulloa.orderservice.entity.Order;
import com.ulloa.orderservice.entity.OrderDetail;
import com.ulloa.orderservice.entity.OrderStatus;
import com.ulloa.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setCustomerId(orderDto.getCustomerId());
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(new Date());
        order.setDetails(orderDto.getDetails()
                .stream()
                .map(detailDto -> mapToEntity(detailDto, order))
                .collect(Collectors.toList()));

        Order savedOrder = orderRepository.save(order);
        return mapToDto(savedOrder);
    }

    private OrderDto mapToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomerId());
        dto.setStatus(order.getStatus().name());
        dto.setOrderDate(order.getOrderDate());
        dto.setDetails(order.getDetails()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList()));
        return dto;
    }

    private OrderDetailDto mapToDto(OrderDetail detail) {
        OrderDetailDto dto = new OrderDetailDto();
        dto.setProductId(detail.getProductId());
        dto.setQuantity(detail.getQuantity());
        dto.setPrice(detail.getPrice());
        return dto;
    }

    private OrderDetail mapToEntity(OrderDetailDto dto, Order order) {
        OrderDetail detail = new OrderDetail();
        detail.setProductId(dto.getProductId());
        detail.setQuantity(dto.getQuantity());
        detail.setPrice(dto.getPrice());
        detail.setOrder(order);
        return detail;
    }

}
