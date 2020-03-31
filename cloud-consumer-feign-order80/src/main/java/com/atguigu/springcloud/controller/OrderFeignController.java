package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/feign/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String feignTimeout(){
        // openfeign-ribbon，客户端一般默认等待一秒钟
        // 但是我们在服务提供者一段，使用sleep方法，使其运行了3秒
        log.info("**********feignTimeout");
//        long id =3;
//        CommonResult<Payment> paymentById = paymentFeignService.getPaymentById(id);
        return paymentFeignService.paymentFeignTimeout();
    }
}
