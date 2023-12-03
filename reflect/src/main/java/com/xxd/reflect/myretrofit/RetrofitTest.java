package com.xxd.reflect.myretrofit;

import java.util.Arrays;
import java.util.List;

public class RetrofitTest {

    public static void main(String[] args) {
        MyRetrofit myRetrofit = new MyRetrofit.Builder()
                .baseUrl("www.baidu.com")
                .tag("轰炸机")
                .build();

        INetRepository iNetRepository = myRetrofit.create(INetRepository.class);
        String s = iNetRepository.requestBaidu(3);
        String s2 = iNetRepository.requestGoogle("法外狂徒", "kill");
        String number = iNetRepository.getNumber();
        System.out.printf("s=%s,strings=%s%n", s, s2);
    }
}
