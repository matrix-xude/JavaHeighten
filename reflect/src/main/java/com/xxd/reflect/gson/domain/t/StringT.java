package com.xxd.reflect.gson.domain.t;

public class StringT extends BaseT<String> {

    @Override
    public String getData() {
        return t;
    }

    @Override
    public void setData(String s) {
        this.t = s;
    }
}
