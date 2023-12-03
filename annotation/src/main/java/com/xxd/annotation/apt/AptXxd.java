package com.xxd.annotation.apt;

import org.xxd.annotation.XxdColor;
import org.xxd.annotation.XxdType;

/**
 * 测试自己的apt
 * Xxd开头的注解
 */
@XxdType(value = {3,4},describe = "航母")
@XxdColor(color = "红色")
public class AptXxd {

    public static void main(String[] args) {

    }

    @XxdType(value = {1,3,5},describe = "战列舰")
    private static void fun1(){

    }
}
