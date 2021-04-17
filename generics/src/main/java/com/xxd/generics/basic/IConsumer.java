package com.xxd.generics.basic;

/**
 * 泛型的3处用法之一：接口
 */
public interface IConsumer<T> {

    /**
     * 接收一个 T 类型的变量
     * @param t 这里不是泛型，是类型参数
     */
    void accept(T t);
}
