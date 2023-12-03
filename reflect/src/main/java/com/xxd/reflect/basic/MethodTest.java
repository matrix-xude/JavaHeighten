package com.xxd.reflect.basic;

import com.xxd.reflect.basic.domain.IntObtain;
import com.xxd.reflect.basic.domain.Ints;
import com.xxd.reflect.basic.entity.ReflectEntity;
import com.xxd.reflect.basic.utils.PrintUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;

/**
 * 测试 Method 类 API
 */
public class MethodTest {


    public static void main(String[] args) {
        ReflectEntity<String> entity = new ReflectEntity<>();
        Method[] declaredMethods = entity.getClass().getDeclaredMethods();
        Arrays.stream(declaredMethods).forEach(method -> {
//            PrintUtil.printOneLine(method.getName());
            if (method.getName().equals("test")) {
                analysisMember(method);
//                analysisAnnotationElement(method);
//                analysisAccessibleObject(method);
//                annotatedClass(method);
            }
        });
    }

    // 分析Member接口的方法
    public static void analysisMember(Method method) {
        Class<?> declaringClass = method.getDeclaringClass(); // 获取到的是 ReflectEntity，而不是 Field
        String name = method.getName();
        int modifiers = method.getModifiers();
        boolean synthetic = method.isSynthetic();

        String formatStr = "%s -> %s : %s";
        String info1 = String.format(formatStr, "Method", "getDeclaringClass()", declaringClass.toGenericString());
        String info2 = String.format(formatStr, "Method", "getName()", name);
        String info3 = String.format(formatStr, "Method", "getModifiers()", modifiers);
        String info4 = String.format(formatStr, "Method", "isSynthetic()", synthetic);
        PrintUtil.printInfos(info1, info2, info3, info4);
    }

    // 分析AnnotationElement接口的方法
    public static void analysisAnnotationElement(Method method) {

        boolean present1 = method.isAnnotationPresent(IntObtain.class);
        boolean present2 = method.isAnnotationPresent(Ints.class);

        IntObtain a1 = method.getAnnotation(IntObtain.class);
        IntObtain a2 = method.getDeclaredAnnotation(IntObtain.class);
        IntObtain[] arr1 = method.getAnnotationsByType(IntObtain.class);
        IntObtain[] arr2 = method.getDeclaredAnnotationsByType(IntObtain.class);

        Ints a3 = method.getAnnotation(Ints.class);
        Ints a4 = method.getDeclaredAnnotation(Ints.class);
        Ints[] arr3 = method.getAnnotationsByType(Ints.class);
        Ints[] arr4 = method.getDeclaredAnnotationsByType(Ints.class);

        Annotation[] arr5 = method.getAnnotations();
        Annotation[] arr6 = method.getDeclaredAnnotations();

        String formatStr = "%s -> %s (%s) : %s";

        String info1 = String.format(formatStr, "Method", "isAnnotationPresent", "IntObtain.class", present1);
        String info2 = String.format(formatStr, "Method", "isAnnotationPresent", "Ints.class", present2);
        String info3 = String.format(formatStr, "Method", "getAnnotation", "IntObtain.class", a1);
        String info4 = String.format(formatStr, "Method", "getDeclaredAnnotation", "IntObtain.class", a2);
        String info5 = String.format(formatStr, "Method", "getAnnotationsByType", "IntObtain.class", arr1.length);
        String info6 = String.format(formatStr, "Method", "getDeclaredAnnotationsByType", "IntObtain.class", arr2.length);
        String info7 = String.format(formatStr, "Method", "getAnnotation", "Ints.class", a3);
        String info8 = String.format(formatStr, "Method", "getDeclaredAnnotation", "Ints.class", a4);
        String info9 = String.format(formatStr, "Method", "getAnnotationsByType", "Ints.class", arr3.length);
        String info10 = String.format(formatStr, "Method", "getDeclaredAnnotationsByType", "Ints.class", arr4.length);
        String info11 = String.format(formatStr, "Method", "getAnnotations", "", Arrays.toString(arr5));
        String info12 = String.format(formatStr, "Method", "getDeclaredAnnotations", "", Arrays.toString(arr6));

        PrintUtil.printInfos(info1, info2, info3, info4, info5, info6, info7, info8, info9, info10, info11, info12);
    }

    // 分析AccessibleObject类的方法
    private static void analysisAccessibleObject(Method method) {

        method.setAccessible(false);
        boolean accessible = method.isAccessible();
        boolean b = method.trySetAccessible();

        Object access1 = new String("abc");
        Object access2 = new ReflectEntity<>();
        // boolean c1 = field.canAccess(access1); // 报错，IllegalArgumentException
        boolean c2 = method.canAccess(access2);

        String formatStr = "%s -> %s (%s) : %s";

        String info1 = String.format(formatStr, "Method", "isAccessible", "()", accessible);
        String info2 = String.format(formatStr, "Method", "trySetAccessible", "()", b);
        //  String info3 = String.format(formatStr, "Method", "canAccess", "String", c1);
        String info3 = String.format(formatStr, "Method", "canAccess", "ReflectEntity<?>", c2);

        PrintUtil.printInfos(info1, info2, info3);
    }

    // 分析 GenericDeclaration 接口
    private static void analysisGenericDeclaration(Method method) {
        // 与Constructor的 GenericDeclaration 接口分析相同，不再举例
    }

    private static void annotatedClass(Method method) {
        // 打印普通注解 结论：找不到 ElementType.TYPE_USE 类型的注解
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Arrays.stream(parameterAnnotations).forEach(annotations -> PrintUtil.printOneLine(Arrays.toString(annotations)));

        // AnnotatedType类找不到 非ElementType.TYPE_USE 注解
        AnnotatedType[] annotatedParameterTypes = method.getAnnotatedParameterTypes();
        Arrays.stream(annotatedParameterTypes)
                .filter(annotatedType -> annotatedType.getAnnotations().length <= 4)
                .forEach(MethodTest::analysisAnnotatedType);

//         getAnnotatedReceiverType() 方法到底做了什么，返回了该方法所在的类,static方法返回null
        AnnotatedType annotatedReceiverType = method.getAnnotatedReceiverType();
        if (annotatedReceiverType != null)
            analysisAnnotatedType(annotatedReceiverType);
    }

    // 分析 AnnotatedType 接口下的4种类型对应
    private static void analysisAnnotatedType(AnnotatedType annotatedType) {
        // 是否有外部内
        AnnotatedType annotatedOwnerType = annotatedType.getAnnotatedOwnerType();
        PrintUtil.printOneLine("是否有外部类型 : " + (annotatedOwnerType != null));
        PrintUtil.printAnnotatedType(annotatedType);
        PrintUtil.printOneLine("-----------------------------------------------------");

    }


}
