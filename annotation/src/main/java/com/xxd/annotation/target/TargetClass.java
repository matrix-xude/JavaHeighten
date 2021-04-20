package com.xxd.annotation.target;

import com.xxd.annotation.target.domain.*;

/**
 * 测试annotation的target使用范围
 * 方法：用以下12个annotation粘贴到测试的使用位置，去除报红的
 *
 * @NoTargetAnnotation
 * @TypeAnnotation
 * @FieldAnnotation
 * @MethodAnnotation
 * @ParameterAnnotation
 * @ConstructorAnnotation
 * @LocalVariableAnnotation
 * @AnnotationTypeAnnotation
 * @PackageAnnotation
 * @TypeParameterAnnotation
 * @TypeUseAnnotation
 * @ModuleAnnotation
 */

// 类上可以使用3个
@NoTargetAnnotation
@TypeAnnotation
@TypeUseAnnotation
public class TargetClass
        // 泛型上可以使用2个，泛型限定只能使用1个
        <@TypeParameterAnnotation @TypeUseAnnotation T, U extends @TypeUseAnnotation Number>
        // 父类泛型上也只能使用1个
        implements TargetInterface<@TypeUseAnnotation U> {


    // 以下3个成员变量使用的泛型都为3个
    @NoTargetAnnotation
    @FieldAnnotation
    @TypeUseAnnotation
    private T[] arr;

    @NoTargetAnnotation
    @FieldAnnotation
    @TypeUseAnnotation
    private TargetInterface<@TypeUseAnnotation U> target; // TypeUse 使用范围很大

    // 构造函数上可以使用3种
    @NoTargetAnnotation
    @ConstructorAnnotation
    @TypeUseAnnotation
    public TargetClass() {
    }

    // 方法上可以使用2个
    @NoTargetAnnotation
    @MethodAnnotation
    public
    // 方法发型与类方法一样，2个
    <@TypeParameterAnnotation @TypeUseAnnotation K>
    // 返回值前可使用1个
    @TypeUseAnnotation String fun(
            // 方法参数前可使用3个
            @NoTargetAnnotation @ParameterAnnotation @TypeUseAnnotation int a,
            @NoTargetAnnotation @ParameterAnnotation @TypeUseAnnotation T t,
            @NoTargetAnnotation @ParameterAnnotation @TypeUseAnnotation TargetInterface
                    // 连泛型限定 ？ 前都可以加1个
                    <@TypeUseAnnotation  ? extends @TypeUseAnnotation K @TypeUseAnnotation []> target1) {

        // 局部变量前可以使用3个
        @NoTargetAnnotation
        @LocalVariableAnnotation
        @TypeUseAnnotation
        String str1 = "abc";
        return str1;

    }


}
