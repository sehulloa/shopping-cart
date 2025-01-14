package com.ulloa.orderservice.dto;

import lombok.Data;

@Data
public class OrderDetailDto {
    private Long productId;
    private int quantity;
    private double price;
}

