package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long,String> hashMap = new HashMap<>();
    static{
        hashMap.put(1L,"serial001");
        hashMap.put(2L,"serial002");
        hashMap.put(3L,"serial003");
        hashMap.put(4L,"serial004");
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult paymentSQL(@PathVariable("id")Long id){
        String payment = hashMap.get(id);
        CommonResult result = new CommonResult(200,"from mysql,serverPort：" + serverPort,payment);
        return result;
    }



}
