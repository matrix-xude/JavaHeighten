package com.xxd.reflect.gson;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.xxd.reflect.basic.utils.PrintUtil;
import com.xxd.reflect.gson.domain.ArrayEntity;
import com.xxd.reflect.gson.domain.NoNullParameterEntity;

/**
 * 通过Gson了解反射中的序列化、反序列化 Array 问题
 */
public class GsonArrayTest {

    private static Gson gson = new Gson();

    public static void main(String[] args) {
//        serializableArray();
        deserializableArray();
    }

    /**
     * 数组序列化测试
     * {"arr":[3,5,1]}
     * {"arr":[[312,64,13],[3,7,1]]}
     * {"arr":[[312,64,13],[3,7,1]],"objects":[1,"agc",{"i":11,"s":"aaa","doubles":[3.2,1.4]}]}
     */
    private static void serializableArray() {

        ArrayEntity entity = new ArrayEntity();
        // arr 字段赋值
        entity.arr = new int[2][3];
        entity.arr[0] = new int[]{312, 64, 13};
        entity.arr[1] = new int[]{3, 7, 1};

        // objects 字段赋值
        entity.objects = new Object[]{1, "agc", new NoNullParameterEntity(11, "aaa", new double[]{3.2, 1.4})};

        String s = gson.toJson(entity);
        // {"arr":[3,5,1]} 数组被序列化之后
        PrintUtil.printInfos(s);
    }

    /**
     * 数组反序列化测试
     */
    private static void deserializableArray() {
//        String json = "{\"arr\":[[312,\"64\",13],[3,7,1,4]]}";
        String json = "{\"arr\":[[312,64,13],[3,7,1]],\"objects\":[1,\"agc\",{\"i\":11,\"s\":\"aaa\",\"doubles\":[3.2,1.4]}]}";

        ArrayEntity entity = gson.fromJson(json, ArrayEntity.class);
        LinkedTreeMap linkedTreeMap = (LinkedTreeMap) entity.objects[2];
        double i = (double) linkedTreeMap.get("i");
        PrintUtil.printInfos(entity.toString());

    }
}
