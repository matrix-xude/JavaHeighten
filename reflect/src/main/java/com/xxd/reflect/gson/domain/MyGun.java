package com.xxd.reflect.gson.domain;

public class MyGun extends BaseGun {

    public MyGun() {
    }

    public MyGun(int i, String s) {
        this.i = i;
        this.s = s;
    }

    @Override
    public void shot() {
        System.out.println("biubiu,喷发！");
    }

    @Override
    public String toString() {
        return "MyGun{" +
                "i=" + i +
                ", s='" + s + '\'' +
                '}';
    }
}
