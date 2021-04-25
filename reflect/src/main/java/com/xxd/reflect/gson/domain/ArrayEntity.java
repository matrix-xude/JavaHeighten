package com.xxd.reflect.gson.domain;

import java.util.Arrays;

public class ArrayEntity {

    public int[][] arr;
    public Object[] objects;

    @Override
    public String toString() {
        return "ArrayEntity{" +
                "arr=" + Arrays.toString(arr) +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }
}
