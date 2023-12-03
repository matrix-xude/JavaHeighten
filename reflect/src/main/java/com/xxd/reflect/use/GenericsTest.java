package com.xxd.reflect.use;

import com.xxd.reflect.basic.utils.PrintUtil;
import com.xxd.reflect.use.entity.BaseGun;
import com.xxd.reflect.use.entity.MyGun;

import java.lang.reflect.*;
import java.util.Arrays;

/**
 * 测试获取 泛型的签名字段 signature
 */
public class GenericsTest {

    public static void main(String[] args) {
//        fatherClass();
        sonClass();
//        sonT();
    }

    /**
     * 父类获取
     */
    private static void fatherClass() {
        Class<BaseGun> baseGunClass = BaseGun.class;
        PrintUtil.printClass(baseGunClass);

        // [T] 可以知道父类有泛型信息
        showTypeParameters(baseGunClass);
    }

    /**
     * 子类获取
     */
    private static void sonClass() {
        // 创建一个base类的子类
        Class<?> baseGunClass = new BaseGun<String,String>(){}.getClass();
        PrintUtil.printClass(baseGunClass);

        // [] 子类没有泛型信息
        showTypeParameters(baseGunClass);
        // 子类去拿父类的泛型信息
        // com.xxd.reflect.use.entity.BaseGun<java.lang.String> 拿到了真实类型
        Type genericSuperclass = baseGunClass.getGenericSuperclass();
        PrintUtil.printInfos(genericSuperclass.getTypeName());
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) genericSuperclass;
            Type[] actualTypeArguments = type.getActualTypeArguments();
            // 拿到了类的Class [class java.lang.String]
            PrintUtil.printInfos(Arrays.toString(actualTypeArguments));

            Arrays.stream(actualTypeArguments).forEach(type1 -> {
                if (type1 instanceof Class<?>) {
                    // 具体Class信息拿到手，OK，可以反射等
                    Class<?> myClass = (Class<?>) type1;
                    PrintUtil.printInfos(myClass.getName());
                }
            });
        }
    }

    // 子类直接拿父类的字段t,试试
    private static void sonT() {
        Class<MyGun> myGunClass = MyGun.class;
        try {
            Field t = myGunClass.getField("t");
            // public T com.xxd.reflect.use.entity.BaseGun.t
            String s = t.toGenericString();
            // public java.lang.Object com.xxd.reflect.use.entity.BaseGun.t
            String s1 = t.toString();
            PrintUtil.printInfos(s, s1);

            // 直接拿字段还是不能获取到泛型信息的，需要转type
            Type genericType = t.getGenericType();
            // 父类直接拿泛型T
            TypeVariable<?>[] typeParameters = BaseGun.class.getTypeParameters();
            // 是否对应的上类上的泛型，如果能，就可以直接根据子类的映射，获取真实类型  结论：可以拿到
            PrintUtil.printInfos(genericType.getTypeName(),String.format("当前字段的Type和类上的Type[0]对应吗？ ：%s",typeParameters[0] == genericType ));
            PrintUtil.printInfos(genericType.getTypeName(),String.format("当前字段的Type和类上的Type[1]对应吗？ ：%s",typeParameters[1] == genericType ));
            if (genericType instanceof TypeVariable<?>) {
                TypeVariable<?> typeVariable = (TypeVariable<?>) genericType;
                // 这样不行，还是泛型信息
                Type[] bounds = typeVariable.getBounds();
                PrintUtil.printInfos(Arrays.toString(bounds));

                // 获取定义泛型的地方
                GenericDeclaration declaration = typeVariable.getGenericDeclaration();
                if (declaration instanceof Class<?>) {
                    Class<?> tClass = (Class<?>) declaration;
                    PrintUtil.printClass(tClass);
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    // 展示自己的泛型信息
    private static void showTypeParameters(Class<?> aClass) {
        TypeVariable<? extends Class<?>>[] typeParameters = aClass.getTypeParameters();
        PrintUtil.printInfos(Arrays.toString(typeParameters));
    }


    // 打印父类的泛型信息
    private static void showGenericSuper(Class<?> aClass) {
        Type genericSuperclass = aClass.getGenericSuperclass();
        Type[] genericInterfaces = aClass.getGenericInterfaces();
        PrintUtil.printInfos(genericSuperclass.getTypeName());
        PrintUtil.printInfos(Arrays.toString(genericInterfaces));
    }
}
