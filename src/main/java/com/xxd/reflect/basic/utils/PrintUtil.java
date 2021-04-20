package com.xxd.reflect.basic.utils;

import com.xxd.reflect.basic.domain.DescObtain;
import com.xxd.reflect.basic.domain.DoubleObtain;
import com.xxd.reflect.basic.domain.IntObtain;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * 打印工具类
 */
public class PrintUtil {

    public static void printInfos(String... infos) {
        Arrays.stream(infos).forEach(PrintUtil::printOneLine);
        System.out.println("----------------------------------------------------");
    }

    public static void printOneLine(String info) {
        System.out.printf("%s\n", info);
    }


    public static void printAnnotation(Annotation... annotations) {
        Arrays.stream(annotations).forEach(annotation -> {
            if (annotation == null) {
                printOneLine("当前获取到了一个null的注解");
            } else if (annotation instanceof DescObtain) {
                DescObtain a1 = (DescObtain) annotation;
                printOneLine(String.format("注解类名%s , desc = %s", a1.annotationType().getSimpleName(), a1.desc()));
            } else if (annotation instanceof IntObtain) {
                IntObtain a2 = (IntObtain) annotation;
                printOneLine(String.format("注解类名%s , value = %s", a2.annotationType().getSimpleName(), a2.value()));
            } else if (annotation instanceof DoubleObtain) {
                DoubleObtain a3 = (DoubleObtain) annotation;
                printOneLine(String.format("注解类名%s , value = %s", a3.annotationType().getSimpleName(), a3.value()));
            } else {
                printOneLine(String.format("注解类名%s , 还未匹配的注解", annotation.annotationType().getSimpleName()));
            }
        });
        System.out.println("----------------------------------------------------");
    }
}
