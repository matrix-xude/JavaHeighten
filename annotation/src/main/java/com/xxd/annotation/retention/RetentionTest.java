package com.xxd.annotation.retention;

import com.xxd.annotation.retention.domain.SourceRetention;
import com.xxd.annotation.retention.domain.ClassRetention;
import com.xxd.annotation.retention.domain.RuntimeRetention;
import com.xxd.annotation.retention.domain.NoRetention;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * 测试4种保留级别的注解
 * 可知默认的注解是 Class 级别
 */
@SourceRetention
@ClassRetention
@RuntimeRetention
@NoRetention
public class RetentionTest {

    public static void main(String[] args) {
        Annotation[] annotations = RetentionTest.class.getAnnotations();
        Arrays.stream(annotations).forEach(annotation -> {
            System.out.println(annotation.annotationType());
        });
    }
}
