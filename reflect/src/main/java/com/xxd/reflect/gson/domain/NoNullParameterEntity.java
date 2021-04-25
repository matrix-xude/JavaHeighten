package com.xxd.reflect.gson.domain;

import com.xxd.reflect.basic.utils.PrintUtil;

public class NoNullParameterEntity {

    public int i;
    public String s;
    public double[] doubles;

//    private NoNullParameterEntity() {
//        PrintUtil.printOneLine("反序列化走到这个null参数构造函数了！！！");
//    }

    public NoNullParameterEntity(int i, String s, double[] doubles) {
        this.i = i;
        PrintUtil.printOneLine("反序列化走到这个3参数构造函数了！！！");
        this.s = s;
        this.doubles = doubles;
    }
}
