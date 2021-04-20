package basic.utils;

import basic.domain.DescObtain;
import basic.domain.DoubleObtain;
import basic.domain.IntObtain;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;

/**
 * 打印工具类
 */
public class PrintUtil {

    public static void printInfos(String... infos) {
        Arrays.stream(infos).forEach(PrintUtil::printOneLine);
        System.out.println("----------------------------------------------------");
    }

    public static void printOneLine(String info) {
        System.out.printf("%s\n", info);
    }


    public static void printAnnotation(Annotation... annotations) {
        Arrays.stream(annotations).forEach(annotation -> {
            if (annotation == null) {
                printOneLine("当前获取到了一个null的注解");
            } else if (annotation instanceof DescObtain) {
                DescObtain a1 = (DescObtain) annotation;
                printOneLine(String.format("注解类名%s , desc = %s", a1.annotationType().getSimpleName(), a1.desc()));
            } else if (annotation instanceof IntObtain) {
                IntObtain a2 = (IntObtain) annotation;
                printOneLine(String.format("注解类名%s , value = %s", a2.annotationType().getSimpleName(), a2.value()));
            } else if (annotation instanceof DoubleObtain) {
                DoubleObtain a3 = (DoubleObtain) annotation;
                printOneLine(String.format("注解类名%s , value = %s", a3.annotationType().getSimpleName(), a3.value()));
            } else {
                printOneLine(String.format("注解类名%s , 还未匹配的注解", annotation.annotationType().getSimpleName()));
            }
        });
        System.out.println("----------------------------------------------------");
    }

    /**
     * 打印AnnotatedType类的信息
     * @param annotatedType
     */
    public static void printAnnotatedType(AnnotatedType annotatedType) {
        String format = "当前类型是：%s -> %s";
        if (annotatedType == null) {
            String info = String.format(format, "null", "");
            PrintUtil.printOneLine(info);
            return;
        }
        if (annotatedType instanceof AnnotatedParameterizedType) {
            AnnotatedParameterizedType type = (AnnotatedParameterizedType) annotatedType;
            String info = String.format(format, "AnnotatedParameterizedType", type);
            printTypeInfo(annotatedType, info);
            AnnotatedType[] arguments = type.getAnnotatedActualTypeArguments();
            Arrays.stream(arguments).forEach(PrintUtil::printAnnotatedType);
        } else if (annotatedType instanceof AnnotatedTypeVariable) {
            AnnotatedTypeVariable type = (AnnotatedTypeVariable) annotatedType;
            String info = String.format(format, "AnnotatedTypeVariable", type);
            printTypeInfo(annotatedType, info);
            AnnotatedType[] arguments = type.getAnnotatedBounds();
            Arrays.stream(arguments).forEach(PrintUtil::printAnnotatedType);
        } else if (annotatedType instanceof AnnotatedArrayType) {
            AnnotatedArrayType type = (AnnotatedArrayType) annotatedType;
            String info = String.format(format, "AnnotatedArrayType", type);
            printTypeInfo(annotatedType, info);
            AnnotatedType arguments = type.getAnnotatedGenericComponentType();
            printAnnotatedType(arguments);
        } else if (annotatedType instanceof AnnotatedWildcardType) {
            AnnotatedWildcardType type = (AnnotatedWildcardType) annotatedType;
            String info = String.format(format, "AnnotatedWildcardType", type);
            printTypeInfo(annotatedType, info);
            AnnotatedType[] arguments = type.getAnnotatedLowerBounds();
            AnnotatedType[] arguments2 = type.getAnnotatedUpperBounds();
            Arrays.stream(arguments).forEach(PrintUtil::printAnnotatedType);
            Arrays.stream(arguments2).forEach(PrintUtil::printAnnotatedType);
        } else {
            String info = String.format(format, "未知类型", annotatedType);
            printTypeInfo(annotatedType, info);
        }

    }

    private static void printTypeInfo(AnnotatedType annotatedType, String info) {
        PrintUtil.printOneLine(info);
        Annotation[] annotations = annotatedType.getAnnotations();
        String str = String.format("当前上面的注解有： %s", Arrays.toString(annotations));
        PrintUtil.printOneLine(str);
        Type typeInfo = annotatedType.getType();
        PrintUtil.printOneLine("当前getType()类型： "+typeInfo.getTypeName());
    }
}
