package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    // 这个方法用于获取所有的服务实例，并装在形参里面
    ServiceInstance instances(List<ServiceInstance> serviceInstances);

}
