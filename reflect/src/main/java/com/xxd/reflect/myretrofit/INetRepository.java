package com.xxd.reflect.myretrofit;

import com.xxd.reflect.myretrofit.domain.MyGET;
import com.xxd.reflect.myretrofit.domain.MyPOST;
import com.xxd.reflect.myretrofit.domain.MyParameter;

import java.util.List;

/**
 * 模拟网络接口
 */
public interface INetRepository {

    @MyGET(url = "/baidu/book")
    String requestBaidu(@MyParameter(name = "number") int number);

    @MyPOST(url = "/google/order")
    String requestGoogle(@MyParameter(name = "token") String name, @MyParameter(name = "doIt") String password);

    String getNumber();
}
