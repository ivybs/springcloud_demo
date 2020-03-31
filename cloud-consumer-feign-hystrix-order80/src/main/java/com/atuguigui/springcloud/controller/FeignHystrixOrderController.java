package com.atuguigui.springcloud.controller;

import com.atuguigui.springcloud.service.FeignHystrixOrderService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class FeignHystrixOrderController {
    @Resource
    FeignHystrixOrderService feignHystrixOrderService;

    // 这里用于测试全局的fallback兜底
//    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        // 这里用于测试全局的fallback兜底
//        int a = 10 / 0;
        String result = this.feignHystrixOrderService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        String result = this.feignHystrixOrderService.paymentInfo_Timeout(id);
        return result;
    }
    public String paymentInfo_TimeoutHandler(@PathVariable("id") Integer id){
        return "我是消费者80，对方支付系统繁忙，请10秒钟后再试试，或者自己运行出错检查自己";
    }

    // 这里是全局fallback方法
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试";
    }

}
