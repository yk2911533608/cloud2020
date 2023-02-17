package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("paymentInfo_Ok/{id}")
    public String paymentInfo_Ok(@PathVariable("id") Integer id){
        return paymentService.paymentInfo_Ok(id);
    }

    /**
     * 超过3秒中就会调用fallbackMethod方法
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_timeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    @GetMapping("paymentInfo_timeOut/{id}")
    public String paymentInfo_timeOut(@PathVariable("id") Integer id){
        return paymentService.paymentInfo_TimeOut(id);
    }

    public String paymentInfo_timeOutHandler(Integer id){
        System.out.println("8001兜底的方法");
        return "8001兜底的方法";
    }
}
