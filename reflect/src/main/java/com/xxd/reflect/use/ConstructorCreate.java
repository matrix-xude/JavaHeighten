package com.xxd.reflect.use;

import com.xxd.reflect.basic.utils.PrintUtil;
import com.xxd.reflect.use.entity.MyEntity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 测试Constructor的创建
 */
public class ConstructorCreate {

    public static void main(String[] args) {
        ConstructorCreate constructorCreate = new ConstructorCreate();
        constructorCreate.createInstance();
    }


    // 反射创造新类
    private void createInstance() {

//        createByClass();
        createByConstructor();
//        createByConstructor2();

    }

    /**
     * 通过 class.newInstance() 创建
     * 已经过时，且有异常
     * 1. private 空参数构造函数无法创建
     * 2. 没有空参数构造函数无法创建
     */
    private void createByClass() {
        // 此方法已经过时，且会抛出InstantiationException, IllegalAccessException 异常
        try {
            MyEntity<?> o = MyEntity.class.newInstance(); // 不要使用此方法
            PrintUtil.printInfos(o.toString());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过Constructor创建
     * 直接传入参数
     */
    private void createByConstructor() {
        Class<?> aClass = MyEntity.class;

        // 空参数
        try {
            // 这样获取有风险，不知道是否存在 0 参数的构造函数
            Constructor<?> constructor1 = aClass.getDeclaredConstructor();
            // 为了防止私有的构造函数，必须这样设置
            constructor1.trySetAccessible();
            MyEntity<?> myEntity = (MyEntity<?>) constructor1.newInstance();
            PrintUtil.printInfos(myEntity.toString());
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // 带数组参数
        try {
            // 注意点 1. 基本数据类型是 int.class 不是 Integer.class  2. 数组是 double[].class 类似
            Constructor<?> constructor2 = aClass.getDeclaredConstructor(int.class, String.class, double[].class);
            // 为了防止私有的构造函数，必须这样设置
            constructor2.trySetAccessible();
            MyEntity<?> myEntity = (MyEntity<?>) constructor2.newInstance(11, "aaa", new double[]{3.3, 4.4});
            PrintUtil.printInfos(myEntity.toString());
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // 带泛型参数
        try {
            // 泛型被会擦除成 Object,所以只能使用 Object.class
            Constructor<?> constructor3 = aClass.getDeclaredConstructor(Object.class);
            // 为了防止私有的构造函数，必须这样设置
            constructor3.trySetAccessible();
            MyEntity<?> myEntity = (MyEntity<?>) constructor3.newInstance(new ArrayList<>());
            PrintUtil.printInfos(myEntity.toString());
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // 带List的泛型
        try {
            // 泛型被会擦除成 Object,所以只能使用 List.class
            Constructor<?> constructor4 = aClass.getDeclaredConstructor(List.class);
            // 为了防止私有的构造函数，必须这样设置
            constructor4.trySetAccessible();

            List<String> list = Arrays.asList("ac", "ad", "123c");
            MyEntity<?> myEntity = (MyEntity<?>) constructor4.newInstance(list);
            PrintUtil.printInfos(myEntity.toString());

            // 获取到原始类型是 List<?> 或者说是 List,必须强转才能使用
            List<?> tList = myEntity.tList;

            // 强转随便转，但是之后使用可能会报错
            List<String> stringList = (List<String>) tList;
            List<Integer> integerList = (List<Integer>) tList;

            PrintUtil.printInfos(stringList.toString());

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过Constructor创建2
     * 动态获取构造函数的参数
     */
    private void createByConstructor2() {
        Class<?> aClass = MyEntity.class;

        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();

        Arrays.stream(declaredConstructors)
                .map(Constructor::getParameterTypes)
                .flatMap((Function<Class<?>[], Stream<Class<?>>>) Arrays::stream)
                .forEach(PrintUtil::printClass);

    }


}
