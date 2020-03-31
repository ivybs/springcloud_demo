package com.atguigu.myrule;


import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
当我们要定制自己的负载均衡策略时，官方围挡明确提出：
这个自定义配置类不能放在@ComponentScan所扫描的当前包下以及子包下
否则我们自定义的这个配置类就会被所有的ribbon客户端所共享，达不到特殊化定制的目的了
然后我们发现凡是被@SpringBootApplication标记的，就同时被@ComponentScan所标记
也就是说，模块的启动类所在目录以及子目录下，都不能写我们的自定义配置类
所以配置类所在文件夹为java下的一级子目录
* **/
@Configuration
public class MySelfRules {
    @Bean
    public IRule myRule(){
        // 定义为随机模式
        return new RandomRule();
    }
}
