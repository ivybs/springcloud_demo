package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/*
    负载均衡算法：rest 接口第几次请求数 % 服务器集群总数量 = 时机调用服务器位置下标，每次服务重启后rest 接口技术求从1开始。
List instances = discoverClient.getInstances("PAYMENT-SERVICE");
如： List[0] instances = 127.0.0.1:8002
List[1] instances = 127.0.0.1:8001
8001 + 8002 组合为集群，他们共计2台服务器，集群总数为2 ， 按照轮询算法原理：
当请求总数为1 时：1%2 = 1, 对应下标位置为1， 则获得服务地址为 127.0.0.1：8001
当请求总数为2 时：2%2 = 0, 对应下标位置为1， 则获得服务地址为 127.0.0.1：8002
当请求总数为3 时：2%2 = 1, 对应下标位置为1， 则获得服务地址为 127.0.0.1：8001

依次类推 。。。。
**/
public class MyLB implements LoadBalancer {

    // 真正的访问记录次数器
    private AtomicInteger atomicInteger = new AtomicInteger();

    // 这个方法主要的作用是，获取当前的访问次数
    // 由于又进行了新的一次轮询，所以还需要再当前的访问次数基础上加一
    // 这里同时需要注意的是，由于我不懂juc，以及其中的CAS，后续看完相关教程后，需要返回来看看
    public final int getAndIncrese(){
        int current;
        int next;
        // 自旋锁
        do{
            current = this.atomicInteger.get();
            // 这里2147433647 是int类型的上限
            next = current >= 2147483647 ? 0 : current + 1;
        }
        // 这里的compareAndSet方法：期望值是current，修改值是next
        /*
        总的可以这样理解
        if (this == expect) {
            this = update
            return true;
        } else {
            return false;
        }
        这里是JUC和CAS的知识，后面再来🙅‍
        **/
        //atomicInteger 如果等于当前的访问值那么就加一
        // 如果已经取到了下一个访问的位置，那么就需要跳出当前循环
        while(!this.atomicInteger.compareAndSet(current,next));
        System.out.println("******next : " + next);
        // 这里返回的是总访问次数的结果
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {

        // 计算出下次访问服务的下标
        int index = getAndIncrese() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
