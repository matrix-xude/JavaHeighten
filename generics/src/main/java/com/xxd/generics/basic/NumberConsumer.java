package com.xxd.generics.basic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 泛型的3处用法之二：类
 * T extends Number : 泛型可以进行边界限定
 */
public class NumberConsumer<T extends Number, K> implements IConsumer<K> {

    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public void accept(K k) {
        System.out.printf("%s中接收了到消费信息：%s\r\n", this.getClass().getSimpleName(), k.toString());
    }

    /**
     * 泛型的3处用法之三：方法 （在可见修饰符 与 返回值 之间定义方法接收的泛型）
     * @param u   实际类型参数
     * @param <U> 泛型可以多继承，但是只能继承 1个class,可以继承 n 个接口，且继承类必须写在前面
     * @return 返回自己
     */
    public <U extends Object & Serializable & Cloneable> U genericsMethod(U u) {
        System.out.printf("接收了到实际泛型参数类型是：%s\r\n", u.getClass().getSimpleName());
        return u;
    }

}
