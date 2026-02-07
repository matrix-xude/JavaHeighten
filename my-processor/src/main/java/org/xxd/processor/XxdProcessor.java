package org.xxd.processor;

import org.xxd.annotation.XxdColor;
import org.xxd.annotation.XxdType;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes({"org.xxd.annotation.XxdColor","org.xxd.annotation.XxdType"})
public class XxdProcessor extends AbstractProcessor {

    private Messager messager;  // 打印使用

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // set中获取到的是注册了SupportedAnnotationTypes，并且实际被用到的annotation种类，而不是具体每种的数量
        // 如果set集合第一次就为空集，次方法不会被调用
        printMessage(String.format("开始处理注解,当前获取到的set集合中元素个数=%d", set.size()));
        set.forEach(t -> {
            // XxdType annotation = t.getAnnotation(XxdType.class); 这样无用，而且拿到的为null
            Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(t);
            printMessage(String.format("编译环境找到的%s注解的个数=%d",t.getSimpleName(),elements.size()));
            elements.forEach(e -> {
                // 这里获取到的Element是类、方法这类元素的封装，可以查看getKind()方法，所以元素上获取到的注解可以是多个的
                Name simpleName = e.getSimpleName();
                XxdType annotation1 = e.getAnnotation(XxdType.class);
                XxdColor annotation2 = e.getAnnotation(XxdColor.class);
                printMessage(String.format("当前注解的位置=%s,名字=%s,XxdType=%s,XxdColor=%s",e.getKind(),simpleName,annotation1,annotation2));
            });
        });

        // true: 表示已经处理过 @SupportedAnnotationTypes 上标记过的注解，其它Processor再也拿不到该注解了。 false: 该注解还会被其他Processor拿到。
        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager(); // 父类的变量processingEnv
        printMessage(String.format("%s开始init",this.getClass().getSimpleName()));
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return super.getSupportedAnnotationTypes();
//        此方法可以代替类上的注解SupportedAnnotationTypes，手动返回需要apt的注解的类型
//        return Set.of("org.xxd.annotation.XxdColor","org.xxd.annotation.XxdType");
    }

    private void printMessage(String info) {
        messager.printMessage(Diagnostic.Kind.OTHER, info);
    }

}
