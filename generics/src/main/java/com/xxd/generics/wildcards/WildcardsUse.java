package com.xxd.generics.wildcards;


import com.xxd.generics.wildcards.domain.Apple;
import com.xxd.generics.wildcards.domain.Food;
import com.xxd.generics.wildcards.domain.Fruit;
import com.xxd.generics.wildcards.domain.Plate;

/**
 * 通配符的使用介绍
 */
public class WildcardsUse<T,U extends T> {

    private Plate<?> plate1;
    private Plate<? extends Fruit> plate2;
    private Plate<? super Fruit> plate3;
    private Plate<? extends T> plate4;

    /**
     * 使用通配符 ？ 的盘子
     */
    public void initPlate1() {
        // 下面2句创建是等价的， ？ = ？ extends Object
        plate1 = new Plate<>();
        plate1 = new Plate<Object>();

        // 水果盘可以赋值给该盘子
        plate1 = new Plate<Fruit>();

        // 该盘子既不能存物品，也不能取物品(唯一可取出来当作Object)
        // plate1.add(new Apple()); // 编译不能通过，报红
        // Fruit o = plate1.get(); // 编译不能通过，报红
        Object o = plate1.get();
    }

    /**
     * 使用 ? extends 的盘子
     */
    public void initPlate2() {
        // 苹果盘可以赋值给该盘子
        plate2 = new Plate<Apple>();
        // 食物盘子不能赋值
        // plate2 = new Plate<Food>(); // 编译不能通过，报红

        // 该盘子不能存物品，可以取物品
        // plate2.add(new Apple()); // 编译不能通过，报红
        Fruit fruit = plate2.get();
    }

    /**
     * 使用 ？ super 的盘子
     */
    public void initPlant3() {
        // 苹果盘不能赋值给该盘子
        // plate3 = new Plate<Apple>(); // 编译不能通过，报红
        // 食物盘子可以赋值
        plate3 = new Plate<Food>();

        // 该盘子能存物品，不能取物品
        plate3.add(new Apple());
        // Fruit fruit = plate3.get(); // 编译不能通过，报红
    }

    /**
     * 使用 ？ extends T 的盘子
     *
     * @param t T是泛型类型，不能new，只能传入
     */
    public void initPlant4(T t) {
        plate4 = new Plate<>();
        // plate4 = new Plate<Apple>(); // 编译不能通过，报红
        plate4 = new Plate<U>(); // 可以通过

        // 该盘子不能存物品，可以取物品,与 extends 具体类型无区别
        // plate4.add(t); // 编译不能通过，报红
        T t1 = plate4.get();
    }
}
