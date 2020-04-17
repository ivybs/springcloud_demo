package com.atguigu.springcloud.entities;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;

    private Long userId;

    private Integer count;

    private BigDecimal money;

    /**
     * 订单状态0- 创建， 1-完成
     */
    private Integer status;
}

