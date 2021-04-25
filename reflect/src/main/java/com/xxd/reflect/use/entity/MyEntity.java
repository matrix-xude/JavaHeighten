package com.xxd.reflect.use.entity;

import java.util.Arrays;
import java.util.List;

public class MyEntity<T> {

    public int i;
    public String str;
    public double[] doubles;
    public T t;
    public List<T> tList;


    public MyEntity() {
    }


    public MyEntity(int i, String str, double[] doubles) {
        this.i = i;
        this.str = str;
        this.doubles = doubles;
    }

    public MyEntity(T t) {
        this.t = t;
    }

    public MyEntity(List<T> tList) {
        this.tList = tList;
    }

    @Override
    public String toString() {
        return "MyEntity{" +
                "i=" + i +
                ", str='" + str + '\'' +
                ", doubles=" + Arrays.toString(doubles) +
                ", t=" + t +
                ", tList=" + tList +
                '}';
    }
}
