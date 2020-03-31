package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import com.atguigu.myrule.MySelfRules;

@SpringBootApplication
// 通过这个注解来开启eureka的客户端
@EnableEurekaClient
//通过这个注解来指定ribbon的机制，name属性指向的是当前订单服务要指向的支付服务
// ribbon默认机制为轮询机制
//@RibbonClient(name="CLOUD-PAYMENT-SERVICE",configuration=MySelfRules.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
