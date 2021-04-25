package com.xxd.reflect.gson.domain.t;

public class ClassT<T> extends BaseT<T> {

    @Override
    public T getData() {
        return t;
    }

    @Override
    public void setData(T t) {
        this.t = t;
    }
}
