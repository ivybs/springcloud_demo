package com.atguigu.springcloud.config;

import com.atguigu.springcloud.lb.LoadBalancer;
import com.atguigu.springcloud.lb.MyLB;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {


    /*
    相当于spring中的applicationContext.xml
    其中配置的<bean id="" class="">
    通过使用bean注解的方式向容器中注入
    也就是说此时spring容器中就又了RestTemplate对象
    **/
    // 如果报java.net.UnknownHostException: CLOUD-PAYMENT-SERVICE
    // 可能是因为没有设置负载均衡方式，导致不知道找集群中的哪一个服务提供者提供服务
    //loadbalanced默认的是轮询
    //@LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public LoadBalancer getMyLB(){return new MyLB();}
}
