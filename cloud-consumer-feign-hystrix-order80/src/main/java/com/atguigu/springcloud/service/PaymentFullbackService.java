package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFullbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_ok(Integer id) {
        return "服务调用失败，提示来自：cloud-consumer-feign-order80";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "服务调用失败，提示来自：cloud-consumer-feign-order80";
    }
}
