package com.xxd.annotation.apt;

import com.xxd.apt.annotation.AptAnnotation;

/**
 * 引用了 apt-annotation 和 apt-processor
 * 来测试 apt-processor 是否能检测到此注解
 */
@AptAnnotation(key = "xxd", value = {2, 3, 5})
public class AptTest {

    @AptAnnotation(key = "zl", value = {1, 5, 11})
    public static void main(String[] args) {
        String s;
    }
}
