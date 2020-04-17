package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Order;
import com.atguigu.springcloud.service.OrderService;
import com.atguigu.springcloud.service.impl.OrderSercieImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    private OrderSercieImpl orderServiceImp;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderServiceImp.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}
