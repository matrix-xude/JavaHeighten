package com.xxd.generics.essence;

import java.util.LinkedList;
import java.util.List;

/**
 * 泛型的本质测试
 * 测试是否在运行时刻还存在？
 */
public class GenericsIsRuntime {

    public static void main(String[] args) {
        List<Integer> list1 = new LinkedList<>();
        List<String> list2 = new LinkedList<>();

        System.out.printf("不同类型参数的泛型类型Class是否相同：%b",list1.getClass() == list2.getClass());
    }
}
