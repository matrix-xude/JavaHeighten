package com.xxd.apt.processor;

import com.xxd.apt.annotation.AptAnnotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;

// 需要处理的注解全类名
@SupportedAnnotationTypes("com.xxd.apt.annotation.AptAnnotation")
public class MyProcessor extends AbstractProcessor {

    private Messager messager;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager = processingEnv.getMessager();
        printMessage("开始处理");
        // 真实代码应该只获取@SupportedAnnotationTypes标记的注解即可，下面代码是为了测试是否只能拿到标记的注解
        // Set<? extends Element> set = roundEnv.getElementsAnnotatedWith(AptAnnotation.class);
        // set中获取到的是注册了SupportedAnnotationTypes，并且实际被用到的annotation种类，而不是具体每种的数量
        annotations.forEach(a -> {
            printMessage("Set<? extends TypeElement> annotations 集合之一: " + a.getSimpleName().toString());
            Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(a);
            elementsAnnotatedWith.forEach(element -> {
                // element是对应位置的封装，不用Class是因为此时还没生成 .class文件，获取对应Kind可以向下强转，Kind=Class可以转为TypeElement,从而获取对应类的包名等
                printMessage(String.format(Locale.getDefault(), "element中 Kind=%s, type=%s, toString=%s", element.getKind(), element.asType().toString(), element.toString()));
                AptAnnotation aptAnnotation = element.getAnnotation(AptAnnotation.class);
                printMessage(String.format(Locale.getDefault(), "当前AptAnnotation的key=%s, value=%s", aptAnnotation.key(), Arrays.toString(aptAnnotation.value())));
            });
        });

        // true: 表示已经处理过 @SupportedAnnotationTypes 上标记过的注解，其它Processor再也拿不到该注解了。 false: 该注解还会被其他Processor拿到。
        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {   // 支持的版本，必须让支持到最新版本
        return SourceVersion.latestSupported();
    }

    private void printMessage(String info) {
        if (messager != null)
            messager.printMessage(Diagnostic.Kind.NOTE, info);
    }

}
