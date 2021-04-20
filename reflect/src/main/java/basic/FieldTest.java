package basic;

import basic.domain.IntObtain;
import basic.domain.Ints;
import basic.entity.ReflectEntity;
import basic.utils.PrintUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;

/**
 * 测试 Field 类 API
 */
public class FieldTest {


    public static void main(String[] args) throws NoSuchFieldException {
        ReflectEntity<String> entity = new ReflectEntity<>();
        Field field = entity.getClass().getDeclaredField("t");
//        analysisMember(field);
//        analysisAnnotationElement(field);
//        analysisAccessibleObject(field);
//        analysisGenericDeclaration(field,entity.getClass());
        annotatedClass(entity.getClass());
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
    private static void analysisAccessibleObject(Field field) {

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

        PrintUtil.printInfos(info1, info2, info3);
    }

    // Field 没有 GenericDeclaration 接口，此处分析一下泛型T定义的位置
    private static void analysisGenericDeclaration(Field field, Class<?> aClass) {
        Type genericType = field.getGenericType();
        // 测试字段能否获取到字段前面的Type_Use泛型 结论：不能获取到
        Annotation[] declaredAnnotations1 = field.getAnnotations();
        String info = String.format("%s -> %s (%s) : %s", "Field", "getDeclaredAnnotations", "", Arrays.toString(declaredAnnotations1));
        PrintUtil.printInfos(info);
        if (genericType instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable<?>) genericType;
            String name = typeVariable.getName();
            Type[] bounds = typeVariable.getBounds();
            GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
            boolean equals = genericDeclaration.equals(aClass);
            // 测试 TypeVariable 能否获取到常规注解: 结论 可以获取到注解 (这里拿到的是T注册时的注解，而不是 type_user 类型的注解)
            Annotation[] declaredAnnotations = typeVariable.getDeclaredAnnotations();


            String formatStr = "%s -> %s (%s) : %s";
            String info1 = String.format(formatStr, "TypeVariable<? extends Class<?>>", "getName", "", name);
            String info2 = String.format(formatStr, "TypeVariable<? extends Class<?>>", "getBounds", "", Arrays.toString(bounds));
            String info3 = String.format(formatStr, "TypeVariable<? extends Class<?>>", "getGenericDeclaration", "", genericDeclaration);
            // 类型参数 获取到的 泛型类型 （D extends GenericDeclaration） 其实就是 aClass 自身
            String info4 = String.format(formatStr, "Class<?>", "equals", "Class<?>", equals);
            String info5 = String.format(formatStr, "TypeVariable<? extends Class<?>>", "getDeclaredAnnotations", "", Arrays.toString(declaredAnnotations));

            PrintUtil.printInfos(info1, info2, info3, info4, info5);
        }
    }

    private static void annotatedClass(Class<? extends ReflectEntity> aClass) {
        Field[] declaredFields = aClass.getDeclaredFields();
        Arrays.stream(declaredFields)
                .map(Field::getAnnotatedType)
                .filter(annotatedType -> annotatedType.getAnnotations().length <= 4)
                .forEach(FieldTest::analysisAnnotatedType);
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
