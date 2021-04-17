package com.xxd.generics.type;

import java.util.List;

/**
 * 各种Type类型测试
 */
public class TypeField<T> {

    private int i;
    private T t;
    private List list1;
    private List<T> list2;
    private T[] arr;
    private TypeTop<T, String> typeTop;
    private TypeTop<? extends T, Integer> typeTop2;
    // 最后来个复杂的循环嵌套字段,包含所有类型
    private TypeTop<? super TypeField<T[]>, TypeTop<TypeField<TypeTop<? super Integer, Double>>[], String>> typeTop3;
}
