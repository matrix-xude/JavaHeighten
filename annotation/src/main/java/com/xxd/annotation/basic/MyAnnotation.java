package com.xxd.annotation.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解的定义
 *
 * @interface 就可以定义一个注解
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})  // 保留位置是一个数组
@Retention(RetentionPolicy.RUNTIME)  // 保留级别
public @interface MyAnnotation {
}
