package com.ulloa.paymentservice.dto;

import lombok.Data;

@Data
public class PaymentResponseDto {

    private Long paymentId;
    private String status;
    private double amount;

}
