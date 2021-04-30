package com.atguigu.springcloud;

import com.atguigu.irule.MyIRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@EnableEurekaClient
@SpringBootApplication
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration= MyIRule.class)
public class PaymentMain8000 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8000.class);
    }
}
