package com.xxd.reflect.use;

import com.xxd.reflect.basic.utils.PrintUtil;

import java.lang.reflect.Array;

/**
 * 反射包下的 Array 是创建一切数组相关的类型
 * 非常重要，测试Array 相关 API
 */
public class ArrayTest {

    public static void main(String[] args) {
        createByName();
    }

    /**
     * 测试数组通过名字创建
     */
    private static void createByName() {
        // 数组类型是固定的
        Class<Object> originClass = Object.class;
        Class<?> aClass = originClass.arrayType();
        PrintUtil.printClass(aClass);

        // 因为数组类型的子元素可以为null,所有创建一个数组怎么都不会报异常
        Object o = Array.newInstance(aClass, 2); // 等于创建了一个 Objet[2][]的数组
        PrintUtil.printInfos(o.toString());

        Object o1 = Array.newInstance(originClass, 2, 3); // 等于创建了一个 Objet[2][3]的数组

        // == 成立，2种等价，说明了数组中元素Class相同，就是同一种类型
        PrintUtil.printInfos("2种不同的数组创建类型是否等价 ： " + (o.getClass() == o1.getClass()));

        try {
            // 通过 forName 和 aClass自身比较，发现创建的数组是一样的
            Class<?> aClass1 = Class.forName(aClass.getName());
            PrintUtil.printInfos(aClass1.toString());
            PrintUtil.printInfos(aClass.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static <T> void created() {
        // 以下创建都不行
        // T t = (T) new String();
        // T[] t = new T[3];
    }
}
