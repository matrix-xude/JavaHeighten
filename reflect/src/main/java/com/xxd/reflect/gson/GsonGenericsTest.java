package com.xxd.reflect.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xxd.reflect.basic.utils.PrintUtil;
import com.xxd.reflect.gson.domain.t.*;

import java.lang.reflect.Type;

/**
 * 通过Gson了解反射中的序列化、反序列化 泛型 问题
 */
public class GsonGenericsTest {

    private static Gson gson = new Gson();

    public static void main(String[] args) {
//        serializableT();
//        deserializableT();
        deserializableT2();
    }

    /**
     * 序列化 T
     * {"t":"飞行棋"}
     * 2中序列化都是一样的，没区别
     */
    private static void serializableT() {
        IT<String> iT = new ClassT<>();
        iT.setData("飞行棋");

        String s = gson.toJson(iT);
        PrintUtil.printInfos(s);

        StringT stringT = new StringT();
        stringT.setData("飞行棋2");

        String s2 = gson.toJson(stringT);
        PrintUtil.printInfos(s2);
    }

    /**
     * 反序列化 T
     */
    private static void deserializableT() {
        String json = "{\"t\":\"飞行棋\"}";
        StringT stringT = gson.fromJson(json, StringT.class);
        PrintUtil.printInfos(stringT.toString());

        IT classT = gson.fromJson(json, IT.class);
        PrintUtil.printInfos(classT.toString());
        Object data = classT.getData();
    }

    /**
     * 反序列化 T
     */
    private static void deserializableT2() {
        BaseT<ConcreteEntity> baseT = new ClassT<>();
        baseT.setData(new ConcreteEntity("阿贝多", 234));
        String s = gson.toJson(baseT);
        // {"t":{"str":"阿贝多","score":234},"f":1.5}
        PrintUtil.printInfos(s);

        String json = "{\"t\":{\"str\":\"阿贝多\",\"score\":234},\"f\":1.5}";

        ClassT o1 = gson.fromJson(json, ClassT.class);

        // 使用Gson 提供过的 TypeToken创建 type
        Type type = new TypeToken<ClassT<ConcreteEntity>>() {
        }.getType();
        // 自己 new 子类获取父类的 type ,与上面是等价的，只是TypeToken使用了protected构造函数，必须new 子类，防止你创建了自身
        Type type1 = new ClassT<ConcreteEntity>() {
        }.getClass().getGenericSuperclass();

        Object o2 = gson.fromJson(json, type1);

        // o1 拿到的 t 实际对象是 LinkedTreeMap,因为泛型擦除，不知道具体的T类型
        PrintUtil.printInfos(o1.toString());

        // o2 拿到的 t 实际对象是 ConcreteEntity，因为子类保留了父类的泛型信息，所以用子类的Type可以查找到
        PrintUtil.printInfos(o2.toString());
    }
}
