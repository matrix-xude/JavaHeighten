package com.xxd.reflect.basic.entity;

import com.xxd.reflect.basic.domain.DescObtain;
import com.xxd.reflect.basic.domain.DoubleObtain;
import com.xxd.reflect.basic.domain.IntObtain;

import java.util.List;

/**
 * 测试测试类实体，用来测试反射的各种API
 */
@IntObtain(3)
@DescObtain(desc = "我是类上的")
public class ReflectEntity<@DescObtain(desc = "装逼如风，常伴我身，T") @IntObtain(95533) @DoubleObtain(-1.0) T> extends ReflectBaseEntity implements Cloneable {

    // 5个成员变量
    private int a;
    private double[] arr;
    private String str;
    private T t;
    @IntObtain(52)
    @IntObtain(323)
    @DescObtain(desc = "葫芦娃")
    private @DoubleObtain(1.1d) List<@DoubleObtain(1.2d) ? extends @DoubleObtain(1.3d) T> list;

    private @DoubleObtain(1.1d) List<@DoubleObtain(1.2d) ? extends @DoubleObtain(1.3d) String> list2;


    // 以下来5个构造方法，方便测试构造
    public ReflectEntity() {
    }

    @DescObtain(desc = "我有3个构造函数参数")
    private ReflectEntity(int a, double[] arr, String str) {
        this.a = a;
        this.arr = arr;
        this.str = str;
    }

    @IntObtain(1)
    protected <@DoubleObtain(3.3d) U extends Number> ReflectEntity(T t, U u) {
        this.t = t;
    }

    @IntObtain(2)
    @DescObtain(desc = "wildcard is me , constructor") ReflectEntity(List<? extends T> list) {
        this.list = list;
    }

    public ReflectEntity(int a, double[] arr, String str, T t, List<? extends T> list) {
        this.a = a;
        this.arr = arr;
        this.str = str;
        this.t = t;
        this.list = list;
    }

    // 下面是需要反射测试方法
    @IntObtain(33)
    @DescObtain(desc = "超级简单的加法")
    private int add(int addition) {
        return a + addition;
    }

    protected String[] splitBy(String split) {
        return str.split(split);
    }

    public <@DescObtain(desc = "我描述一个U形的磁铁？？") U> @DoubleObtain(2.71) U getMiddle(@DescObtain(desc = "134") @DoubleObtain(3.1) U u,
                                                                                  @DescObtain(desc = "110") @DoubleObtain(3.14) String a, @IntObtain(1) int i) {
        return u;
    }

    private void accept(@DescObtain(desc = "T一脚") T t) {

    }

    public static void test(@IntObtain(110) @DoubleObtain(3.14) int a) { }
}
