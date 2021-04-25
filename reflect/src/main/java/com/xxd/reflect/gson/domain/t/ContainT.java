package com.xxd.reflect.gson.domain.t;

public class ContainT<U> {

    public int a;
    public String b;
    public ClassT<U> t;

    public ContainT() {
    }

    public ContainT(int a, String b, ClassT<U> t) {
        this.a = a;
        this.b = b;
        this.t = t;
    }

    @Override
    public String toString() {
        return "ContainT{" +
                "a=" + a +
                ", b='" + b + '\'' +
                ", t=" + t +
                '}';
    }
}
