package com.xxd.reflect.use;

import com.xxd.reflect.basic.utils.PrintUtil;
import com.xxd.reflect.use.entity.MyEntity;

/**
 * 创建Class对象测试
 */
public class ClassCreateTest {

    public static void main(String[] args) throws ClassNotFoundException {
//        createByGet();
//        createByClass();
        createByName();
    }

    /**
     * 1.通过getClass()创建
     */
    private static void createByGet() {
        MyEntity<String> stringMyEntity = new MyEntity<>();
        Class<? extends MyEntity> aClass = stringMyEntity.getClass();
        PrintUtil.printClass(aClass);
    }

    /**
     * 2.通过.Class创建
     */
    private static void createByClass() {
        Class<MyEntity> aClass = MyEntity.class;
        PrintUtil.printClass(aClass);
    }

    /**
     * 3.通过 Class.forName()创建
     */
    private static void createByName() throws ClassNotFoundException {
        Class<MyEntity> aClass = MyEntity.class;
        // 查看一个Class的5个name
        String simpleName = aClass.getSimpleName();
        String packageName = aClass.getPackageName();
        String name = aClass.getName();
        String canonicalName = aClass.getCanonicalName();
        String typeName = aClass.getTypeName();
        PrintUtil.printInfos(simpleName, packageName, name, canonicalName, typeName);

        // 这是通过上面获取到的name
        String declareName = "com.xxd.reflect.use.entity.MyEntity";
        Class<?> aClass1 = Class.forName(declareName);
        PrintUtil.printClass(aClass1);

        // Class<?> aClass2 = Class.forName(declareName, true, ClassLoader.getPlatformClassLoader()); // 这个ClassLoader找不到类
        Class<?> aClass2 = Class.forName(declareName, true, ClassLoader.getSystemClassLoader());
        PrintUtil.printClass(aClass2);

        // 测试ClassLoader是否相同 结论：任何Class的ClassLoader都一样，是系统的
        PrintUtil.printInfos((ClassLoader.getSystemClassLoader() == PrintUtil.class.getClassLoader()) + "");
    }
}
