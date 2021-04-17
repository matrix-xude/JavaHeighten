package com.xxd.annotation.apt;

import com.xxd.apt.annotation.AptAnnotation;

@AptAnnotation(key = "xxd", value = {2, 3, 5})
public class AptTest {

    @AptAnnotation(key = "zl", value = {1, 5, 11})
    public static void main(String[] args) {
        String s;
    }
}
