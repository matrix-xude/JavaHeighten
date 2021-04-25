package com.xxd.reflect.gson;

import com.google.gson.Gson;
import com.xxd.reflect.basic.utils.PrintUtil;
import com.xxd.reflect.gson.domain.ArrayEntity;
import com.xxd.reflect.gson.domain.NoNullParameterEntity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 通过Gson了解反射中的序列化、反序列化  对象中 构造函数 的问题
 * 如：没有空参数的构造函数能反序列化吗
 */
public class GsonConstructorTest {

    private static Gson gson = new Gson();

    public static void main(String[] args) {
//        reflect();
//        serializableObject();
        deserializableObject();
    }

    /**
     * 反射也是要走构造函数的
     */
    private static void reflect() {
//        try {
//            NoNullParameterEntity entity = NoNullParameterEntity.class.newInstance();
//        } catch (InstantiationException | IllegalAccessException e) {
//            e.printStackTrace();
//        }

        Constructor<NoNullParameterEntity> constructor = null;
        try {
            constructor = NoNullParameterEntity.class.getDeclaredConstructor(int.class,String.class,double[].class);
            constructor.trySetAccessible();
            NoNullParameterEntity entity = constructor.newInstance(1,"afdaf",new double[]{11.11});
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    /**
     * 数组序列化Object
     * {"i":1,"s":"aaa","doubles":[3.2,1.6]}
     */
    private static void serializableObject() {
        NoNullParameterEntity entity = new NoNullParameterEntity(1, "aaa", new double[]{3.2, 1.6});
        String s = gson.toJson(entity);
        PrintUtil.printInfos(s);
    }

    /**
     * 数组反序列化Object
     * 可以反射无null参数的构造函数，
     * 如果没有null参数的构造函数，不会去找其它构造函数，
     * 反射是兜底机制，而中间有很多构造器，这涉及到Gson的架构问题
     */
    private static void deserializableObject() {
        String json = "{\"i\":1,\"s\":\"aaa\",\"doubles\":[3.2,1.6]}";
        Object entity = gson.fromJson(json, NoNullParameterEntity.class);
        PrintUtil.printInfos(entity.toString());
    }
}
