package com.xxd.generics.essence;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型擦除带来的副作用
 */
public class NegativeEffect<T> {

    public static void main(String[] args) {

    }

    // 泛型类型变量不能使用基本数据类型
    public static void negative1() {
        // List<int> list = new ArrayList<int>(); // 报错,不能编译
        List<Integer> list2 = new ArrayList<Integer>();
    }

    // 不能使用instanceof 运算符
    public static void negative2() {
        List<Integer> list = new ArrayList<>();
        // if (list instanceof ArrayList<Integer>){ } // 报错,不能编译

        // 唯一可以用的地方，判断是否是 ArrayList类型
        if (list instanceof ArrayList<?>) {

        }
    }

    // 泛型在静态方法和静态类中不能使用类上的泛型
    public static <U> void negative3() {
        // T t; //  报错,找不到T

        // 方法泛型可以使用
        U t;
    }

    // 泛型类型中的方法冲突,擦除后与Object的方法一样了
    // public boolean equals(T t){ } 报错

    // 没法创建泛型实例
    public void negative5() {
        //  T t = new T();  // 报错,不能编译
    }

    // 没有泛型数组
    public void negative6() {

        List<Integer>[] arr = null;
        // arr = new ArrayList<Integer>[2]; // 报错，Generic array creation

        // 假设泛型数组存在，由于数组满足协变，可以转换为Object[]，这样就出现了安全问题
        Object[] objects = arr;
        objects[0] = new ArrayList<Integer>();
        objects[1] = new ArrayList<String>();

        // 有一种情况可以创建,因为 <?> 匹配了所有类型，不会发生安全问题
        List<?>[] arr2 = new ArrayList<?>[2];
        arr2[0] = new ArrayList<Integer>();
        arr2[1] = new ArrayList<String>();

        // 利用数组的协变规则 + <?>的可以创建数组，可以创建一种特殊的泛型数组
        arr = (List<Integer>[]) new ArrayList<?>[2]; // 可以通过检测

        // 上面创建的原理
        List<?>[] arr3 = new ArrayList<?>[2]; // <?>的可创建性
        Object[] arr4 = arr3;  // 数组的协变规则，List<?> 是 Object的子类，所以可以转换
        // 类型强转，Unchecked cast,虽然有警告，但是可以编译通过，我们自己知道没有强转错误，所以这样就创建了一个泛型数组
        arr = (List<Integer>[]) arr4;
    }
}
