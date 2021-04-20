package com.xxd.reflect.basic;

import com.xxd.reflect.basic.domain.IntObtain;
import com.xxd.reflect.basic.domain.Ints;
import com.xxd.reflect.basic.entity.ReflectEntity;
import com.xxd.reflect.basic.utils.PrintUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

/**
 * 测试 Constructor 类 API
 */
public class ConstructorTest {


    public static void main(String[] args)  {
        ReflectEntity<String> entity = new ReflectEntity<>();
        Constructor<?>[] declaredConstructors = entity.getClass().getDeclaredConstructors();
        Arrays.stream(declaredConstructors).forEach( constructor -> {
            int parameterCount = constructor.getParameterCount();
            if (parameterCount == 2){
//                analysisMember(constructor);
//                analysisAnnotationElement(constructor);
//                analysisAccessibleObject(constructor);
                analysisGenericDeclaration(constructor);
            }
        });
    }

    // 分析Member接口的方法
    public static void analysisMember(Constructor<?> constructor) {
        Class<?> declaringClass = constructor.getDeclaringClass();
        String name = constructor.getName();
        int modifiers = constructor.getModifiers();
        boolean synthetic = constructor.isSynthetic();

        String formatStr = "%s -> %s : %s";
        String info1 = String.format(formatStr, "Constructor", "getDeclaringClass()", declaringClass.toGenericString());
        String info2 = String.format(formatStr, "Constructor", "getName()", name);
        String info3 = String.format(formatStr, "Constructor", "getModifiers()", modifiers);
        String info4 = String.format(formatStr, "Constructor", "isSynthetic()", synthetic);
        PrintUtil.printInfos(info1, info2, info3, info4);
    }

    // 分析AnnotationElement接口的方法
    public static void analysisAnnotationElement(Constructor<?> constructor) {

        boolean present1 = constructor.isAnnotationPresent(IntObtain.class);
        boolean present2 = constructor.isAnnotationPresent(Ints.class);

        IntObtain a1 = constructor.getAnnotation(IntObtain.class);
        IntObtain a2 = constructor.getDeclaredAnnotation(IntObtain.class);
        IntObtain[] arr1 = constructor.getAnnotationsByType(IntObtain.class);
        IntObtain[] arr2 = constructor.getDeclaredAnnotationsByType(IntObtain.class);

        Ints a3 = constructor.getAnnotation(Ints.class);
        Ints a4 = constructor.getDeclaredAnnotation(Ints.class);
        Ints[] arr3 = constructor.getAnnotationsByType(Ints.class);
        Ints[] arr4 = constructor.getDeclaredAnnotationsByType(Ints.class);

        Annotation[] arr5 = constructor.getAnnotations();
        Annotation[] arr6 = constructor.getDeclaredAnnotations();

        String formatStr = "%s -> %s (%s) : %s";

        String info1 = String.format(formatStr, "Constructor", "isAnnotationPresent", "IntObtain.class", present1);
        String info2 = String.format(formatStr, "Constructor", "isAnnotationPresent", "Ints.class", present2);
        String info3 = String.format(formatStr, "Constructor", "getAnnotation", "IntObtain.class", a1);
        String info4 = String.format(formatStr, "Constructor", "getDeclaredAnnotation", "IntObtain.class", a2);
        String info5 = String.format(formatStr, "Constructor", "getAnnotationsByType", "IntObtain.class", arr1.length);
        String info6 = String.format(formatStr, "Constructor", "getDeclaredAnnotationsByType", "IntObtain.class", arr2.length);
        String info7 = String.format(formatStr, "Constructor", "getAnnotation", "Ints.class", a3);
        String info8 = String.format(formatStr, "Constructor", "getDeclaredAnnotation", "Ints.class", a4);
        String info9 = String.format(formatStr, "Constructor", "getAnnotationsByType", "Ints.class", arr3.length);
        String info10 = String.format(formatStr, "Constructor", "getDeclaredAnnotationsByType", "Ints.class", arr4.length);
        String info11 = String.format(formatStr, "Constructor", "getAnnotations", "", Arrays.toString(arr5));
        String info12 = String.format(formatStr, "Constructor", "getDeclaredAnnotations", "", Arrays.toString(arr6));

        PrintUtil.printInfos(info1, info2, info3, info4, info5, info6, info7, info8, info9, info10, info11, info12);
    }

    // 分析AccessibleObject类的方法
    private static void analysisAccessibleObject(Constructor<?> constructor){

        constructor.setAccessible(false);
        boolean accessible = constructor.isAccessible();
        boolean b = constructor.trySetAccessible();

        String access1 = new String("abc");
        ReflectEntity<?> access2 = null;  // Constructor 与其它不同，必须接收一个null的对象，接收存在的对象报错
        // boolean c1 = field.canAccess(access1); // 报错，IllegalArgumentException
        boolean c2 = constructor.canAccess(access2);

        String formatStr = "%s -> %s (%s) : %s";

        String info1 = String.format(formatStr, "Constructor", "isAccessible", "()", accessible);
        String info2 = String.format(formatStr, "Constructor", "trySetAccessible", "()", b);
        //  String info3 = String.format(formatStr, "Constructor", "canAccess", "String", c1);
        String info3 = String.format(formatStr, "Constructor", "canAccess", "ReflectEntity<?>", c2);

        PrintUtil.printInfos(info1,info2,info3);
    }

    // 分析 GenericDeclaration 接口
    private static void analysisGenericDeclaration(Constructor<?> constructor) {
        TypeVariable<? extends Constructor<?>>[] typeParameters = constructor.getTypeParameters();
        String info = String.format("Constructor -> getTypeParameters() : %s", Arrays.toString(typeParameters));
        PrintUtil.printInfos(info);
        Arrays.stream(typeParameters).forEach(typeVariable -> {
            String name = typeVariable.getName();
            Type[] bounds = typeVariable.getBounds();
            Constructor<?> genericDeclaration = typeVariable.getGenericDeclaration();
            boolean equals = genericDeclaration.equals(constructor);
            // 测试 TypeVariable 能否获取到常规注解: 结论 可以获取到注解
            Annotation[] declaredAnnotations = typeVariable.getDeclaredAnnotations();


            String formatStr = "%s -> %s (%s) : %s";
            String info1 = String.format(formatStr, "TypeVariable<? extends Class<?>>", "getName", "", name);
            String info2 = String.format(formatStr, "TypeVariable<? extends Class<?>>", "getBounds", "", Arrays.toString(bounds));
            String info3 = String.format(formatStr, "TypeVariable<? extends Class<?>>", "getGenericDeclaration", "", genericDeclaration);
            // 类型参数 获取到的 泛型类型 （D extends GenericDeclaration） 其实就是 constructor 自身
            String info4 = String.format(formatStr, "Class<?>", "equals", "Class<?>", equals);
            String info5 = String.format(formatStr, "TypeVariable<? extends Class<?>>", "getDeclaredAnnotations", "Constructor<?>", Arrays.toString(declaredAnnotations));

            PrintUtil.printInfos(info1, info2, info3, info4, info5);
        });
    }


}
