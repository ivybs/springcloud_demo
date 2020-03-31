package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//下面这三个注解来自Lombok
//@Data：作用于类上，是以下注解的集合：@ToString @EqualsAndHashCode @Getter @Setter @RequiredArgsConstructor
@Data
//@AllArgsConstructor：生成全参构造器
@AllArgsConstructor
//@NoArgsConstructor：生成无参构造器；
@NoArgsConstructor
//这个类主要时用于前端，前端只需要先拿到code看看
//是否是200，即成功，如果是200那我们走正常逻辑
//非200，那么就要走异常的逻辑
public class CommonResult<T> {
    //404 错误编码 notfound 返回信息
    private  Integer code;
    private String message;
    private T data;
    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
