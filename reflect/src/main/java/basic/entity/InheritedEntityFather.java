package com.xxd.reflect.basic.entity;

import com.xxd.reflect.basic.domain.DescObtain;
import com.xxd.reflect.basic.domain.IntObtain;

@DescObtain(desc = "我是父类")
@IntObtain(1)
public class InheritedEntityFather {

    @DescObtain(desc = "父类的Field")
    public int a;

    @DescObtain(desc = "方法，父类上")
    public void fun1() {
    }
}
