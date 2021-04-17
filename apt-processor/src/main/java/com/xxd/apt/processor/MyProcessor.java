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
        annotations.forEach(a -> {
            printMessage("Set<? extends TypeElement> annotations 集合之一: " + a.getSimpleName().toString());
            Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(a);
            elementsAnnotatedWith.forEach(element -> {
                // 获取到了所有使用的地方
                printMessage(element.toString());
                AptAnnotation aptAnnotation = element.getAnnotation(AptAnnotation.class);
                printMessage(String.format(Locale.getDefault(), "当前AptAnnotation的key=%s, value=%s", aptAnnotation.key(), Arrays.toString(aptAnnotation.value())));
            });
        });

        return false;
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
