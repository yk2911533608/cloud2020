package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
//统一配置兜底方法
@DefaultProperties(defaultFallback = "paymentInfo_timeOutHandler",commandProperties = {
        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "6000")
})
public class FeignHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;



    @GetMapping(value = "/consumer/payment/get/{id}")
    public String getPaymentById(@PathVariable("id") Long id){
        return paymentHystrixService.getPaymentById(id);
    }

    /**
     * 为方法单独配置兜底方法
     * @HystrixCommand(fallbackMethod = "paymentInfo_timeOutHandler",commandProperties = {
     *             @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
     *     })
     * @return
     */
    /**
     * 不指定兜底方法就走默认的
     * @return
     */
    @HystrixCommand
    @GetMapping(value = "/consumer/payment/paymentInfo_timeOut")
    public String paymentInfo_timeOut(){
        return paymentHystrixService.paymentInfo_timeOut(1);
    }

    public String paymentInfo_timeOutHandler(){
        return"80：兜底方法";
    }
}
