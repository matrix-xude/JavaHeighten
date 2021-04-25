package com.xxd.reflect.gson.domain.t;

public abstract class BaseT<T> implements IT<T> {

    public T t;
    public float f = 1.5f;

    @Override
    public String toString() {
        return "BaseT{" +
                "t=" + t +
                '}';
    }
}
