package com.atguigu.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfo_Ok(Integer id){
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_Ok"+id+"哈哈哈";
    }

    public String paymentInfo_TimeOut(Integer id){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_Ok"+id+"耗时3秒钟";
    }
}
