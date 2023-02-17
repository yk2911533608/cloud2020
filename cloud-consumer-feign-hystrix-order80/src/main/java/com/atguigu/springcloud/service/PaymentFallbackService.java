package com.atguigu.springcloud.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String getPaymentById(Long id) {
        return "PaymentFallbackService--------ok";
    }

    @Override
    public String paymentInfo_timeOut(Integer id) {
        return "PaymentFallbackService--------timeOut";
    }
}
