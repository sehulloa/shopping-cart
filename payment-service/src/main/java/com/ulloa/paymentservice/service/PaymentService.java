package com.ulloa.paymentservice.service;

import com.ulloa.paymentservice.dto.PaymentRequestDto;
import com.ulloa.paymentservice.dto.PaymentResponseDto;

public interface PaymentService {

    PaymentResponseDto processPayment(PaymentRequestDto request);
}
