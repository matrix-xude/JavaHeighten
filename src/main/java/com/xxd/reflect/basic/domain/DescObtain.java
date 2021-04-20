package com.xxd.reflect.basic.domain;

import java.lang.annotation.*;

/**
 * 获取描述
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DescObtain {

    String desc();
}
