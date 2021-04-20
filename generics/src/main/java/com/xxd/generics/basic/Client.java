package com.xxd.generics.basic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * 使用泛型
 */
public class Client {

    public static void main(String[] args) {

        // 使用接口泛型
        useInterfaceGenerics("随意春芳歇");

        // 使用接口泛型
        useClassGenerics();

        // 使用方法泛型
        useMethodGenerics();

    }

    /**
     * 使用接口泛型
     */
    private static void useInterfaceGenerics(String info) {
        // 这里使用String 会使得 t 自动推断为String类型
        IConsumer<String> iConsumer = t -> {
            String printInfo = String.format(Locale.getDefault(), "我使用接口泛型传递了数据：%s", info);
            System.out.println(printInfo);
        };
        iConsumer.accept(info);
    }

    /**
     * 使用类泛型
     */
    private static void useClassGenerics() {
        NumberConsumer<Double, String> numberConsumer = new NumberConsumer<>();
        numberConsumer.setT(3.1415926);
        System.out.printf("\r\n当前泛型储存的T数据为:%s\r\n", numberConsumer.getT());
        numberConsumer.accept("王孙自可留");
    }

    /**
     * 使用方法泛型
     * 必须传入与符合方法的参数
     */
    private static void useMethodGenerics() {
        NumberConsumer<Integer, Float> numberConsumer = new NumberConsumer<>();
        //  此处是有泛型推断，可以省略方法应该加的参数
        numberConsumer.genericsMethod(new MethodParam());
        // 完整写法如下
        numberConsumer.<MethodParam>genericsMethod(new MethodParam());
    }

    static class MethodParam implements Serializable, Cloneable {

    }
}
