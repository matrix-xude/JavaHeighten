package com.xxd.reflect.gson;

import com.google.gson.Gson;
import com.xxd.reflect.basic.utils.PrintUtil;
import com.xxd.reflect.gson.domain.BaseGun;
import com.xxd.reflect.gson.domain.IGun;
import com.xxd.reflect.gson.domain.MyGun;

/**
 * 通过Gson了解反射中的序列化、反序列化 Array 问题
 */
public class GsonInterfaceTest {

    private static Gson gson = new Gson();

    public static void main(String[] args) {
//        serializableInterface();
//        deserializableInterface();
//        deserializableAbstractClass();
        deserializableClass();
    }

    /**
     * 接口序列化测试
     * {"i":123,"s":"我的枪"}
     */
    private static void serializableInterface() {
        IGun gun = new MyGun(123,"我的枪");
        String s = gson.toJson(gun);
        PrintUtil.printInfos(s);
    }

    /**
     * 接口反序列化测试
     */
    private static void deserializableInterface() {
        String json = "{\"i\":123,\"s\":\"我的枪\"}";
        IGun gun = gson.fromJson(json, IGun.class);
        PrintUtil.printInfos(gun.toString());
    }

    /**
     * 抽象类反序列化测试
     */
    private static void deserializableAbstractClass() {
        String json = "{\"i\":123,\"s\":\"我的枪\"}";
        BaseGun gun = gson.fromJson(json, BaseGun.class);
        PrintUtil.printInfos(gun.toString());
    }

    /**
     * 具体类反序列化测试
     */
    private static void deserializableClass() {
        String json = "{\"i\":123,\"s\":\"我的枪\"}";
        MyGun gun = gson.fromJson(json, MyGun.class);
        PrintUtil.printInfos(gun.toString());
    }
}
