package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//激活Hsytrix
@EnableCircuitBreaker
@EnableEurekaClient
public class PaymentHsytrixMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHsytrixMain8001.class);
    }
}
