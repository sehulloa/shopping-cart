package com.ulloa.orderservice.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private Long customerId;
    private String status;
    private Date orderDate;
    private List<OrderDetailDto> details;
}
