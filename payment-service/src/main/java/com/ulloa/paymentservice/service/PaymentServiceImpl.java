package com.ulloa.paymentservice.service;

import com.ulloa.orderservice.dto.OrderDto;
import com.ulloa.paymentservice.dto.PaymentRequestDto;
import com.ulloa.paymentservice.dto.PaymentResponseDto;
import com.ulloa.paymentservice.entity.Payment;
import com.ulloa.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;

    @Value("${order-service.url}")
    private String orderServiceUrl;

    public PaymentServiceImpl(PaymentRepository paymentRepository, RestTemplate restTemplate) {
        this.paymentRepository = paymentRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public PaymentResponseDto processPayment(PaymentRequestDto request) {

        String url = orderServiceUrl + "/api/orders/" + request.getOrderId();
        ResponseEntity<OrderDto> responseEntity = restTemplate.getForEntity(url, OrderDto.class);
        OrderDto order = responseEntity.getBody();

        if (order == null) {
            throw new RuntimeException("Order not found for ID: " + request.getOrderId());
        }

        if (!"PENDING".equals(order.getStatus())) {
            throw new RuntimeException("Order is not eligible for payment. Current status: " + order.getStatus());
        }

        Payment payment = new Payment();
        payment.setOrderId(order.getId());
        payment.setAmount(request.getAmount());
        payment.setStatus("SUCCESS");
        payment.setPaymentDate(new Date());

        Payment savedPayment = paymentRepository.save(payment);

        PaymentResponseDto response = new PaymentResponseDto();
        response.setPaymentId(savedPayment.getId());
        response.setAmount(savedPayment.getAmount());
        response.setStatus(savedPayment.getStatus());
        return response;
    }

}
