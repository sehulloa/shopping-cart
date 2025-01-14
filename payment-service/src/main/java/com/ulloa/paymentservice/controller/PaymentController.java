package com.ulloa.paymentservice.controller;

import com.ulloa.paymentservice.dto.PaymentRequestDto;
import com.ulloa.paymentservice.dto.PaymentResponseDto;
import com.ulloa.paymentservice.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDto> processPayment(@RequestBody PaymentRequestDto request) {
        return ResponseEntity.ok(paymentService.processPayment(request));
    }

}
