package com.xxd.reflect.basic.domain;

import java.lang.annotation.*;

/**
 * 获取一个 int 数值的注解
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR,ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Ints.class)
public @interface IntObtain {
    int value();
}
