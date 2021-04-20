package com.xxd.reflect.basic;

import com.xxd.reflect.basic.domain.IntObtain;
import com.xxd.reflect.basic.domain.Ints;
import com.xxd.reflect.basic.entity.ReflectEntity;
import com.xxd.reflect.basic.utils.PrintUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 测试 Field 类 API
 */
public class FieldTest {


    public static void main(String[] args) throws NoSuchFieldException {
        ReflectEntity<String> entity = new ReflectEntity<>();
        Field field = entity.getClass().getDeclaredField("list");
//        analysisMember(field);
//        analysisAnnotationElement(field);
        analysisAccessibleObject(field);
    }

    // 分析Member接口的方法
    public static void analysisMember(Field field) {
        Class<?> declaringClass = field.getDeclaringClass(); // 获取到的是 ReflectEntity，而不是 Field
        String name = field.getName();
        int modifiers = field.getModifiers();
        boolean synthetic = field.isSynthetic();

        String formatStr = "%s -> %s : %s";
        String info1 = String.format(formatStr, "Field", "getDeclaringClass()", declaringClass.toGenericString());
        String info2 = String.format(formatStr, "Field", "getName()", name);
        String info3 = String.format(formatStr, "Field", "getModifiers()", modifiers);
        String info4 = String.format(formatStr, "Field", "isSynthetic()", synthetic);
        PrintUtil.printInfos(info1, info2, info3, info4);
    }

    // 分析AnnotationElement接口的方法
    public static void analysisAnnotationElement(Field field) {

        boolean present1 = field.isAnnotationPresent(IntObtain.class);
        boolean present2 = field.isAnnotationPresent(Ints.class);

        IntObtain a1 = field.getAnnotation(IntObtain.class);
        IntObtain a2 = field.getDeclaredAnnotation(IntObtain.class);
        IntObtain[] arr1 = field.getAnnotationsByType(IntObtain.class);
        IntObtain[] arr2 = field.getDeclaredAnnotationsByType(IntObtain.class);

        Ints a3 = field.getAnnotation(Ints.class);
        Ints a4 = field.getDeclaredAnnotation(Ints.class);
        Ints[] arr3 = field.getAnnotationsByType(Ints.class);
        Ints[] arr4 = field.getDeclaredAnnotationsByType(Ints.class);

        Annotation[] arr5 = field.getAnnotations();
        Annotation[] arr6 = field.getDeclaredAnnotations();

        String formatStr = "%s -> %s (%s) : %s";

        String info1 = String.format(formatStr, "Field", "isAnnotationPresent", "IntObtain.class", present1);
        String info2 = String.format(formatStr, "Field", "isAnnotationPresent", "Ints.class", present2);
        String info3 = String.format(formatStr, "Field", "getAnnotation", "IntObtain.class", a1);
        String info4 = String.format(formatStr, "Field", "getDeclaredAnnotation", "IntObtain.class", a2);
        String info5 = String.format(formatStr, "Field", "getAnnotationsByType", "IntObtain.class", arr1.length);
        String info6 = String.format(formatStr, "Field", "getDeclaredAnnotationsByType", "IntObtain.class", arr2.length);
        String info7 = String.format(formatStr, "Field", "getAnnotation", "Ints.class", a3);
        String info8 = String.format(formatStr, "Field", "getDeclaredAnnotation", "Ints.class", a4);
        String info9 = String.format(formatStr, "Field", "getAnnotationsByType", "Ints.class", arr3.length);
        String info10 = String.format(formatStr, "Field", "getDeclaredAnnotationsByType", "Ints.class", arr4.length);
        String info11 = String.format(formatStr, "Field", "getAnnotations", "", Arrays.toString(arr5));
        String info12 = String.format(formatStr, "Field", "getDeclaredAnnotations", "", Arrays.toString(arr6));

        PrintUtil.printInfos(info1, info2, info3, info4, info5, info6, info7, info8, info9, info10, info11, info12);
    }

    // 分析AccessibleObject类的方法
    private static void analysisAccessibleObject(Field field){

        field.setAccessible(false);
        boolean accessible = field.isAccessible();
        boolean b = field.trySetAccessible();

        Object access1 = new String("abc");
        Object access2 = new ReflectEntity<>();
        // boolean c1 = field.canAccess(access1); // 报错，IllegalArgumentException
        boolean c2 = field.canAccess(access2);

        String formatStr = "%s -> %s (%s) : %s";

        String info1 = String.format(formatStr, "Field", "isAccessible", "()", accessible);
        String info2 = String.format(formatStr, "Field", "trySetAccessible", "()", b);
        //  String info3 = String.format(formatStr, "Field", "canAccess", "String", c1);
        String info3 = String.format(formatStr, "Field", "canAccess", "ReflectEntity<?>", c2);

        PrintUtil.printInfos(info1,info2,info3);
    }


}
