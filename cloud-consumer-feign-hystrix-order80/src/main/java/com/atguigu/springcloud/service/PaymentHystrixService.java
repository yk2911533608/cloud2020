package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
/**
 * 指定fallback = PaymentFallbackService.class服务降级执行调用的方法
 */
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping("paymentInfo_Ok/{id}")
    String getPaymentById(@PathVariable("id") Long id);

    @GetMapping("paymentInfo_timeOut/{id}")
    String paymentInfo_timeOut(@PathVariable("id") Integer id);

}
