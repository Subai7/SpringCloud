package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {

        log.info("*****:" + payment);
        int result = paymentService.create(payment);
        log.info("*****插入操作返回结果:" + result);

        if (result>0){
            return new CommonResult(200,"",payment);
        }else {
            return new CommonResult(444,"",null);

        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {

        Payment payment = paymentService.getPaymentById(id);

        log.info("**********查询结果"+payment);

        if(payment !=null){
            return new CommonResult(200,"查询成功，端口号："+serverPort,payment);
        }else {
            return new CommonResult(444,"查询失败：id:"+id,null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        //获取当前Eureka 注册中心里一共有多少个微服务
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("服务名称："+service);
        }

        //获取指定微服务名称内的所有的微服务实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        //可以通过实例获取到详细信息
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return discoveryClient;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut()
    {
        System.out.println("*****paymentFeignTimeOut from port: "+serverPort);
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        return serverPort;
    }

    @GetMapping("/payment/lb")
    public String lb(){
        return serverPort;
    }

}
