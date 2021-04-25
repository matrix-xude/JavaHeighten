package basic;

import basic.domain.DescObtain;
import basic.entity.InheritedEntitySon;
import basic.utils.PrintUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 测试可以继承的注解 @interface DescObtain 在哪些Member可以继承获取
 */
public class InheritedTest {

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        Class<InheritedEntitySon> aClass = InheritedEntitySon.class;

        fun2(aClass);
    }

    private static void fun1(Class<?> aClass){
        // 测试类上，结论：可以获取到继承类的注解,不能获取到实现接口的注解
        Annotation[] annotations = aClass.getAnnotations();
        Annotation[] annotations2 = aClass.getDeclaredAnnotations();
        PrintUtil.printAnnotation(annotations);
        PrintUtil.printAnnotation(annotations2);
    }

    private static void fun2(Class<?> aClass){
        DescObtain annotation = aClass.getAnnotation(DescObtain.class);
        DescObtain declaredAnnotation = aClass.getDeclaredAnnotation(DescObtain.class);
        PrintUtil.printAnnotation(annotation);
        PrintUtil.printAnnotation(declaredAnnotation);
    }

    private static void fun3(Class<?> aClass) throws NoSuchMethodException {
        // 测试方法，结论：不能继承
        Method method = aClass.getMethod("fun1");
        PrintUtil.printAnnotation(method.getAnnotations());
        PrintUtil.printAnnotation(method.getDeclaredAnnotations());
    }

    private static void fun4(Class<?> aClass) throws NoSuchFieldException {
        // 测试Filed，结论：不能继承
        Field a = aClass.getField("a");
        PrintUtil.printAnnotation(a.getAnnotations());
        PrintUtil.printAnnotation(a.getDeclaredAnnotations());
    }


}
