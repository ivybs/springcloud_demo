package com.atguigui.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(444,"按用户自定义，global----------1",new Payment(3030L,"serial002"));
    }


    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(444,"按用户自定义，global----------2",new Payment(3030L,"serial002"));
    }
}
