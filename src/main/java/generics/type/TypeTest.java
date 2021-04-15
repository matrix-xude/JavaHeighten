package generics.type;

import java.lang.reflect.*;
import java.util.Arrays;

public class TypeTest {

    public static void main(String[] args) {
        TypeField<Double> typeTest = new TypeField<>();
        printField(typeTest.getClass().getDeclaredFields());
    }

    /**
     * 打印字段的Type类型
     *
     * @param declaredFields 字段
     */
    public static void printField(Field[] declaredFields) {
        Arrays.stream(declaredFields).forEach(field -> {
            Type genericType = field.getGenericType();
            System.out.printf("字段名：%s\r\n字段类型：%s\r\n", field.getName(), field.getType());
            printType(genericType);
            System.out.println("-------------------------------------------------------");
        });
    }

    /**
     * 循环打印 type 直到，类型为 Class 结束
     * @param type instanceof来区分5种 Type
     */
    public static void printType(Type type) {
        if (type instanceof Class<?>) {
            System.out.printf("已经到了最终Class类型:%s\r\n", ((Class<?>) type).getName());
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            System.out.printf("当前是ParameterizedType类型：%s\r\n", parameterizedType.getTypeName());
            Arrays.stream(parameterizedType.getActualTypeArguments()).forEach(TypeTest::printType);
        } else if (type instanceof TypeVariable<?>) {
            TypeVariable<?> typeVariable = (TypeVariable<?>) type;
            System.out.printf("当前是TypeVariable类型：%s\r\n", typeVariable.getTypeName());
            Arrays.stream(typeVariable.getBounds()).forEach(TypeTest::printType);
        } else if (type instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) type;
            System.out.printf("当前是GenericArrayType类型：%s\r\n", genericArrayType.getTypeName());
            printType(genericArrayType.getGenericComponentType());
        } else if (type instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type;
            System.out.printf("当前是WildcardType类型：%s\r\n", wildcardType.getTypeName());
            Arrays.stream(wildcardType.getLowerBounds()).forEach(TypeTest::printType);
            Arrays.stream(wildcardType.getUpperBounds()).forEach(TypeTest::printType);
        }
    }
}
