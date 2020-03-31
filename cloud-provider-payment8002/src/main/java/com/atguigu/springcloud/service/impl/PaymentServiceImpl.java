package com.atguigu.springcloud.service.impl;


import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    // resource和autoviwer都可以进行依赖注入
    // resource注解是java自带的
    @Resource
    private PaymentDao paymentDao;

    // 添加
    public int create(Payment payment){
        return  this.paymentDao.create(payment);
    }
    // 通过id查找
    public Payment getPaymentById(Long id){
        return this.paymentDao.getPaymentById(id);
    }
}
