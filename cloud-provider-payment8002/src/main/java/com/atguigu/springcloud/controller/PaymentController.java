package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.impl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
//从Lombok中引入，用于打日志
@Slf4j
public class PaymentController {
    @Resource
    private PaymentServiceImpl paymentService;

    @Value("${server.port}")
    private String serverPort;

    // 写操作使用Post
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = this.paymentService.create(payment);
        // 自己写的时候，可以在控制台打印，但是如果去企业工作
        // 那么就需要写入日志文件
        log.info("******插入结果： " + result);
        if(result > 0){
            return new CommonResult(200,"插入数据库成功, serverPort :" + serverPort,null);
        }else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }


    // 读操作使用Get
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id){

        Payment result = this.paymentService.getPaymentById(id);
        // 自己写的时候，可以在控制台打印，但是如果去企业工作
        // 那么就需要写入日志文件
        log.info("******查询结果： " + result);
        if(result!=null){
            return new CommonResult(200,"查询成功serverPort: "+ serverPort,result);
        }else{
            return new CommonResult(444,"没有对应记录，查询id : " + id,null);
        }
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
//        int timer = 0;
//        while (true){
//            if (timer >= 1000){
//                break;
//            }else {
//                timer++;
//            }
//        }
        return serverPort;
    }

}
