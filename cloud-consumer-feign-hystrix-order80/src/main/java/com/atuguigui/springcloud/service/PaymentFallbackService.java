package com.atuguigui.springcloud.service;

import org.springframework.stereotype.Component;

// 这里是为了给服务降级的fallback解耦所设立的每个方法一旦出现需要服务降级的情况时，所作的兜底操作
// 在这个类里面继承的所有方法里面所对应的都是对应service中每个方法所对应的具体服务降级操作
@Component
public class PaymentFallbackService implements FeignHystrixOrderService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "------ PaymentFallbackService fallback-paymentInfo_OK ------";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "------ PaymentFallbackService fallback-paymentInfo_Timeout ------";
    }
}
