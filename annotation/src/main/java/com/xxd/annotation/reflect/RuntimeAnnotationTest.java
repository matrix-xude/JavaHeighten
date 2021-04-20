package com.xxd.annotation.reflect;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 运行时注解测试
 * 测试反射
 */
public class RuntimeAnnotationTest {

    @ReflectAnnotation(30)
    private int i;

    private int j;

    public static void main(String[] args) {
        Class<RuntimeAnnotationTest> runtimeTestClass = RuntimeAnnotationTest.class;
        Field[] fields = runtimeTestClass.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            ReflectAnnotation annotation = field.getAnnotation(ReflectAnnotation.class);
            if (annotation != null) {
                field.setAccessible(true);
                int value = annotation.value();
                System.out.println("当前ReflectAnnotation的数值是：" + value);
                // 可以给field 反射赋值等操作，这里不再展示
            }
        });
    }
}
