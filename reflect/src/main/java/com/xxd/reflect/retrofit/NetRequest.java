package com.xxd.reflect.retrofit;

import com.xxd.reflect.retrofit.component.GET;
import com.xxd.reflect.retrofit.component.POST;
import com.xxd.reflect.retrofit.component.Parameter;

public interface NetRequest {

    @GET("student/score")
    String getScore(String name, @Parameter(name = "好好读书的人") int number);

    @POST("teacher/exam")
    String putScore(@Parameter(name = "科目是") String examName, @Parameter(name = "grade") double score);

    @GET("aaa/bbb")
    Integer test();

    String test2();
}
