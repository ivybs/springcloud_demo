package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//下面这三个注解来自Lombok
//@Data：作用于类上，是以下注解的集合：@ToString @EqualsAndHashCode @Getter @Setter @RequiredArgsConstructor
@Data
//@AllArgsConstructor：生成全参构造器
@AllArgsConstructor
//@NoArgsConstructor：生成无参构造器；
@NoArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
