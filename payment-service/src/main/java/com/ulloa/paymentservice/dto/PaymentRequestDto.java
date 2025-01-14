package com.ulloa.paymentservice.dto;

import lombok.Data;

@Data
public class PaymentRequestDto {

    private Long orderId;
    private double amount;

}
