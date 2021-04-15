package generics.wildcards;

import generics.wildcards.domain.Apple;
import generics.wildcards.domain.Food;
import generics.wildcards.domain.Fruit;
import generics.wildcards.domain.Plate;

/**
 * 通配符的作用演示
 */
public class WildcardsEffect {

    public static void main(String[] args) {

        // 准备好盘子和食物
        Plate<Apple> applePlate = new Plate<>();
        applePlate.add(new Apple());
        Plate<Fruit> fruitPlate = new Plate<>();
        fruitPlate.add(new Fruit());
        Plate<Food> foodPlate = new Plate<>();
        foodPlate.add(new Food());

        // 把苹果盘子的物品移动到水果盘里
        moveAll(applePlate,fruitPlate);

        // 把水果盘子的物品移动到食物盘里
        moveAll(fruitPlate,foodPlate);

        // 把食物盘里的物品移动到苹果盘里,此移动会有安全问题，在编译时就不能通过了
        // moveAll(foodPlate,applePlate); 编译错误，报红
    }

    // 把一个苹果盘子的苹果移动到另一个苹果盘子
    public static void moveApple(Plate<Apple> src, Plate<Apple> dest) {
        dest.add(src.get(0));
    }

    // 把香蕉移动，水果移动等
    public static <T> void moveT(Plate<T> src, Plate<T> dest) {
        dest.add(src.get(0));
    }

    // 只要符合条件的都能移动
    public static <T> void moveAll(Plate<? extends T> src, Plate<? super T> dest) {
        dest.add(src.get(0));
    }
}
