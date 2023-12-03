package com.xxd.reflect.myretrofit;

import com.xxd.reflect.myretrofit.domain.MyGET;
import com.xxd.reflect.myretrofit.domain.MyPOST;
import com.xxd.reflect.myretrofit.domain.MyParameter;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class MyRetrofit {

    private String baseUrl;
    private String tag;

    private MyRetrofit(String baseUrl, String tag) {
        if (baseUrl == null || baseUrl.isEmpty())
            throw new IllegalArgumentException("baseUrl不能为null");
        this.baseUrl = baseUrl;
        this.tag = tag == null ? "默认tag" : tag;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> aClass) {

        T t = (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{aClass}, new InvocationHandler() {
            @Override
            public String invoke(Object proxy, Method method, Object[] args) throws Throwable {
                StringBuilder sb = new StringBuilder();
                sb.append(tag);
                sb.append(" -> ");
                sb.append(baseUrl);

                MyGET myGET = method.getDeclaredAnnotation(MyGET.class);
                MyPOST myPOST = method.getDeclaredAnnotation(MyPOST.class);
                if (myGET != null) {
                    sb.append(myGET.url());
                    sb.append(" GET方法运行了 ");
                } else if (myPOST != null) {
                    sb.append(myPOST.url());
                    sb.append(" POST方法运行了 ");
                } else {
                    System.out.printf("当前方法不是GET or POST方法,%s\n", method.getName());
                    return null;
                }

                // 这2个数组的长度相等
                Parameter[] parameters = method.getParameters();  // 这个可以获取到字段名字，但是当前我不适用此名字
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                if (args != null) {
                    for (int i = 0; i < args.length; i++) {
                        MyParameter myParameter = (MyParameter)parameterAnnotations[i][0];  // 这里简略写法了，不考虑报错
                        sb.append(myParameter.name()).append("=").append(args[i].toString()).append(" ");
                    }
                }
                System.out.println(sb);


                return sb.toString();
            }
        });
        return t;
    }

    public static class Builder {

        private String baseUrl;
        private String tag;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public MyRetrofit build() {
            return new MyRetrofit(baseUrl, tag);
        }
    }
}
