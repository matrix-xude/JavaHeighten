package com.xxd.annotation.basic;

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

}
