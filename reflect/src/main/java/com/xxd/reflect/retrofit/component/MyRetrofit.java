package com.xxd.reflect.retrofit.component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * 我自己的Retrofit
 * 不实现网络请求，实现传入一个接口，得到一个实现类，拿到接口里的方法参数
 */
public class MyRetrofit {

    private String baseUrl;
    private String tag;

    private MyRetrofit(Builder builder) {
        if (builder.baseUrl == null)
            throw new IllegalArgumentException("baseUrl can't be null");

        // 处理下结尾
        baseUrl = builder.baseUrl;
        if (!baseUrl.endsWith("/"))
            baseUrl += "/";

        tag = builder.tag == null ? "默认者" : builder.tag;
    }

    /**
     * 传入接口的 class 对象，获取到代理
     */
    public <T> T create(Class<T> aClass) {
        if (aClass == null)
            return null;

        T t = (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{aClass}, new InvocationHandler() {
            @Override
            public String invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 拿到所有数据，拼接打印数据
                StringBuilder sb = new StringBuilder();
                sb.append(tag).append(" -> \n");

                // 处理 GET POST 注解
                if (method.getDeclaredAnnotation(GET.class) != null) {
                    String value = method.getDeclaredAnnotation(GET.class).value();
                    sb.append("GET请求发起，url=").append(baseUrl).append(value).append("\n");
                } else if (method.getDeclaredAnnotation(POST.class) != null) {
                    String value = method.getDeclaredAnnotation(POST.class).value();
                    sb.append("POST请求发起，url=").append(baseUrl).append(value).append("\n");
                } else {
                    throw new IllegalArgumentException("这不是一个GET or POST 请求");
                }

                // 处理参数
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                java.lang.reflect.Parameter[] parameters = method.getParameters();

                if (args != null){
                    for (int i = 0; i < args.length; i++) {
                        String parameterName = null; // 参数名
                        Annotation[] parameterAnnotation = parameterAnnotations[i];

                        // 用注解的 name 字段当做参数名
                        if (parameterAnnotation.length != 0) {
                            Annotation annotation = parameterAnnotation[0];
                            if (annotation instanceof Parameter) {
                                Parameter p = (Parameter) annotation;
                                parameterName = p.name();
                            }
                        }

                        // 拿不到name字段就用本身的参数字段名
                        if (parameterName == null) {
                            parameterName = parameters[i].getName();
                        }

                        if (i == 0) {
                            sb.append("参数是 ： {");
                            sb.append(parameterName).append("=").append(args[i].toString());
                        } else if (i == args.length - 1) {
                            sb.append(", ");
                            sb.append(parameterName).append("=").append(args[i].toString());
                            sb.append("}");
                        } else {
                            sb.append(", ");
                            sb.append(parameterName).append("=").append(args[i].toString());
                        }
                    }
                }

                return sb.toString();
            }
        });

        return t;
    }

    public static class Builder {
        private String baseUrl = null;
        private String tag = null;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public MyRetrofit build() {
            return new MyRetrofit(this);
        }
    }
}
