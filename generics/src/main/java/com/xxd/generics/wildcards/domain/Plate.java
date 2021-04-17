package com.xxd.generics.wildcards.domain;

import java.util.LinkedList;

/**
 * 盘子，用来放食物
 */
public class Plate<T> {

    private final LinkedList<T> list = new LinkedList<>();

    public void add(T t) {
        list.offer(t);
    }

    public T get(int index) {
        return list.poll();
    }
}
