package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;


    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback")
//    @SentinelResource(value = "fallback", fallback = "handlerFallback") //fallbakc只负责业务异常
    @SentinelResource(value = "fallback",blockHandler = "blockhandler", fallback = "handlerFallback") // blockhandler只负责sentinel控制台配置违规
//    @SentinelResource(value = "fallback",blockHandler = "blockhandler", fallback = "handlerFallback",
//                      exceptionsToIngore = {IllegalArgumentException.class})// 假如报该异常，不再有fallback方法兜底，没有降级效果了
    public CommonResult fallback(@PathVariable("id")Long id){
        CommonResult result = restTemplate.getForObject(SERVICE_URL+"/paymentSQL/"+id,CommonResult.class,id);
        if (id == 4){
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常");
        }else if (result.getData()==null){
            throw new NullPointerException("NullPointException,该Id没有对应记录，空指针异常");
        }
        return result;
    }

    public CommonResult handlerFallback(@PathVariable Long id,Throwable e){
        Payment payment = new Payment(id,"null");
        return new CommonResult(444,"兜底异常handlerFallback" + e.getMessage(),payment);
    }

    public CommonResult blockhandler(@PathVariable Long id, BlockException blockException){
        Payment payment = new Payment(id,"null");
        return new CommonResult(445,"blockhandler-sentinel限流："+blockException.getMessage(),payment);

    }


    //============openFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id")Long id){
        return paymentService.paymentSQL(id);
    }


}
