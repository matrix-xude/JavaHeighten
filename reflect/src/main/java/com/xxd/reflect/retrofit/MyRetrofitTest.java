package com.xxd.reflect.retrofit;

import com.xxd.reflect.basic.utils.PrintUtil;
import com.xxd.reflect.retrofit.component.MyRetrofit;

/**
 * 测试我自己写的Retrofit框架
 * 结果通过，一个简单的Retrofit模型完成
 */
public class MyRetrofitTest {

    public static void main(String[] args) {

        MyRetrofit myRetrofit = new MyRetrofit.Builder()
                .baseUrl("https://www.xxx.com") // 假装忘记写了结束的 /
                .tag("一条龙")
                .build();

        NetRequest netRequest = myRetrofit.create(NetRequest.class);

        String info = netRequest.getScore("张三", 33);
        PrintUtil.printInfos(info);

        String info2 = netRequest.putScore("数学", 145.5);
        PrintUtil.printInfos(info2);

        // 返回值强转报错，所以正常的Retrofit中加入了转换工厂，Gson,Rxjava的转换之类，就是为了保证返回值的强转正确
        // Integer info3 = netRequest.test();

        // IllegalArgumentException,这不是一个GET or POST 请求
        // String s = netRequest.test2();
    }
}
