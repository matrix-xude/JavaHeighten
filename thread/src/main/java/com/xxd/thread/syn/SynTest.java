package com.xxd.thread.syn;

public class SynTest {

    public static void main(String[] args) {

    }

    private int number;

    public void add() {
        synchronized (SynTest.class) {
            number++;
        }
    }

    public synchronized void minus(){
        number--;
    }
}
