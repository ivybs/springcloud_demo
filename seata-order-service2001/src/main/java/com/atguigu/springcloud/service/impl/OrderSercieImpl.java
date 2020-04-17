package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.OrderDao;
import com.atguigu.springcloud.entities.Order;
import com.atguigu.springcloud.service.AccountService;
import com.atguigu.springcloud.service.OrderService;
import com.atguigu.springcloud.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderSercieImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;


    @Override
    public void create(Order order) {
        System.out.println("开始创建订单");
        orderDao.create(order);
        System.out.println("订单服务开始调用库存，做扣减");
        storageService.decrease(order.getId(),order.getCount());
        System.out.println("账户服务开始调用余额，做扣减");
        accountService.decrease(order.getUserId(),order.getMoney());
        System.out.println("订单创建完成，扣减操作完成");
        System.out.println("修改订单状态");

        orderDao.update(order.getUserId(),0);
        System.out.println("修改订单状态完成");
        System.out.println("下订单任务完成");






    }
}
