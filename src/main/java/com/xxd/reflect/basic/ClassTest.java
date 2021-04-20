package com.xxd.reflect.basic;

import com.xxd.reflect.basic.domain.IntObtain;
import com.xxd.reflect.basic.domain.Ints;
import com.xxd.reflect.basic.entity.ReflectEntity;
import com.xxd.reflect.basic.utils.PrintUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

/**
 * 测试 Class 类 API
 */
public class ClassTest {


    public static<T> void main(String[] args) throws NoSuchFieldException {
        ReflectEntity<String> entity = new ReflectEntity<>();
        Class<? extends ReflectEntity> aClass = entity.getClass();

//        analysisMember(aClass);
//        analysisAnnotationElement(aClass);
//        analysisAccessibleObject(aClass);
        analysisGenericDeclaration(aClass);
    }

    // 分析Member接口的方法
    public static void analysisMember(Class<?> aClass) {

        // Class 并不实现Member接口，但是该有的方法都有，只是 getDeclaringClass() 获取到的是 null，自身就是对应的Class
        Class<?> declaringClass = aClass.getDeclaringClass(); // 获取到的是 ReflectEntity，而不是 Class
        PrintUtil.printOneLine("declaringClass == aClass? : " + (declaringClass == aClass));
        String name = aClass.getName();
        int modifiers = aClass.getModifiers();
        boolean synthetic = aClass.isSynthetic();

        String formatStr = "%s -> %s : %s";
        String info1 = String.format(formatStr, "Class", "getDeclaringClass()", declaringClass);
        String info2 = String.format(formatStr, "Class", "自身", aClass.toGenericString());
        String info3 = String.format(formatStr, "Class", "getName()", name);
        String info4 = String.format(formatStr, "Class", "getModifiers()", modifiers);
        String info5 = String.format(formatStr, "Class", "isSynthetic()", synthetic);
        PrintUtil.printInfos(info1, info2, info3, info4, info5);
    }

    // 分析AnnotationElement接口的方法
    public static void analysisAnnotationElement(Class<?> aClass) {

        boolean present1 = aClass.isAnnotationPresent(IntObtain.class);
        boolean present2 = aClass.isAnnotationPresent(Ints.class);

        IntObtain a1 = aClass.getAnnotation(IntObtain.class);
        IntObtain a2 = aClass.getDeclaredAnnotation(IntObtain.class);
        IntObtain[] arr1 = aClass.getAnnotationsByType(IntObtain.class);
        IntObtain[] arr2 = aClass.getDeclaredAnnotationsByType(IntObtain.class);

        Ints a3 = aClass.getAnnotation(Ints.class);
        Ints a4 = aClass.getDeclaredAnnotation(Ints.class);
        Ints[] arr3 = aClass.getAnnotationsByType(Ints.class);
        Ints[] arr4 = aClass.getDeclaredAnnotationsByType(Ints.class);

        Annotation[] arr5 = aClass.getAnnotations();
        Annotation[] arr6 = aClass.getDeclaredAnnotations();

        String formatStr = "%s -> %s (%s) : %s";

        String info1 = String.format(formatStr, "Class", "isAnnotationPresent", "IntObtain.class", present1);
        String info2 = String.format(formatStr, "Class", "isAnnotationPresent", "Ints.class", present2);
        String info3 = String.format(formatStr, "Class", "getAnnotation", "IntObtain.class", a1);
        String info4 = String.format(formatStr, "Class", "getDeclaredAnnotation", "IntObtain.class", a2);
        String info5 = String.format(formatStr, "Class", "getAnnotationsByType", "IntObtain.class", arr1.length);
        String info6 = String.format(formatStr, "Class", "getDeclaredAnnotationsByType", "IntObtain.class", arr2.length);
        String info7 = String.format(formatStr, "Class", "getAnnotation", "Ints.class", a3);
        String info8 = String.format(formatStr, "Class", "getDeclaredAnnotation", "Ints.class", a4);
        String info9 = String.format(formatStr, "Class", "getAnnotationsByType", "Ints.class", arr3.length);
        String info10 = String.format(formatStr, "Class", "getDeclaredAnnotationsByType", "Ints.class", arr4.length);
        String info11 = String.format(formatStr, "Class", "getAnnotations", "", Arrays.toString(arr5));
        String info12 = String.format(formatStr, "Class", "getDeclaredAnnotations", "", Arrays.toString(arr6));

        PrintUtil.printInfos(info1, info2, info3, info4, info5, info6, info7, info8, info9, info10, info11, info12);
    }

    // 分析AccessibleObject类的方法
    private static void analysisAccessibleObject(Class<?> aClass) {

        // Class 不是 AccessibleObject的子类，没有此中方法
    }

    // 分析 GenericDeclaration 接口
    private static void analysisGenericDeclaration(Class<?> aClass) {
        TypeVariable<? extends Class<?>>[] typeParameters = aClass.getTypeParameters();
        String info = String.format("Class -> getTypeParameters() : %s", Arrays.toString(typeParameters));
        PrintUtil.printInfos(info);
        Arrays.stream(typeParameters).forEach(typeVariable -> {
            String name = typeVariable.getName();
            Type[] bounds = typeVariable.getBounds();
            Class<?> genericDeclaration = typeVariable.getGenericDeclaration();
            boolean equals = genericDeclaration.equals(aClass);
            // 测试 TypeVariable 能否获取到常规注解: 结论 可以获取到注解
            Annotation[] declaredAnnotations = typeVariable.getDeclaredAnnotations();


            String formatStr = "%s -> %s (%s) : %s";
            String info1 = String.format(formatStr, "TypeVariable<? extends Class<?>>", "getName", "", name);
            String info2 = String.format(formatStr, "TypeVariable<? extends Class<?>>", "getBounds", "", Arrays.toString(bounds));
            String info3 = String.format(formatStr, "TypeVariable<? extends Class<?>>", "getGenericDeclaration", "", genericDeclaration);
            // 类型参数 获取到的 泛型类型 （D extends GenericDeclaration） 其实就是 aClass 自身
            String info4 = String.format(formatStr, "Class<?>", "equals", "Class<?>", equals);
            String info5 = String.format(formatStr, "TypeVariable<? extends Class<?>>", "getDeclaredAnnotations", "Class<?>", Arrays.toString(declaredAnnotations));

            PrintUtil.printInfos(info1, info2, info3, info4, info5);
        });
    }


}
