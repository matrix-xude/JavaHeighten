package com.xxd.generics.wildcards;

import com.xxd.generics.wildcards.domain.Apple;
import com.xxd.generics.wildcards.domain.Fruit;
import com.xxd.generics.wildcards.domain.Plate;

/**
 * 泛型类型的继承关系测试
 */
public class GenericsExtendsTest {

    public static void main(String[] args) {

        Plate<Apple> applePlate = new Plate<>();
        Plate<Fruit> fruitPlate = new Plate<>();

        // 虽然 Apple 是 Fruit的子类，但是Plate<Apple> 与 Plate<Fruit>没有任何关系
        // fruitPlate = applePlate;  // 不能如此赋值，报错

    }
}
