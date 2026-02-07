package com.xxd.annotation.basic;

import java.util.Arrays;

// 类上使用注解
@MyAnnotation
public class UseAnnotation {

    // 成员变量上使用注解
    @MyAnnotation
    public int a;

    // 方法上使用注解
    @MyAnnotation
    // 参数上使用注解
    private void fun1(@MyAnnotation String str) {
    }

    public static void main(String[] args) {
        UseAnnotation useAnnotation = new UseAnnotation();
        Arrays.stream(useAnnotation.getClass().getAnnotations()).forEach(annotation ->{
            // 获取class类型拿到的是代理类 class jdk.proxy2.$Proxy
            System.out.println(annotation.getClass());
            // 获取annotationType才会拿到真实注解类 interface com.xxd.annotation.basic.MyAnnotation
            System.out.println(annotation.annotationType());
        });
    }

}
