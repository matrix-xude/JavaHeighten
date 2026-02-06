package com.xxd.generics.wildcards;

import com.xxd.generics.type.SignatureFather;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 通配符可以使用的位置
 * 4种可以使用的位置
 * 3种不能使用的位置
 */
public class WildcardsScope
//        extends SignatureFather<? extends String>  // 不能使用 1.超类型的类型参数（不能用在实现父类的泛型上，类似 implements IConsumer<? extends K> 是不允许的）
{

    /**
     * 1.成员变量可以使用
     */
    private List<? extends Number> list1;

    /**
     * 2.方法参数可以使用
     */
    private void fun1(List<? super Integer> list) {

    }

    /**
     * 3.方法返回值可以使用（但是这不是一个好的习惯，代码中还是应该使用具体返回值）
     */
    private List<? super Integer> fun2() {
        return null;
    }

    private void fun3() {
        // 4.局部变量可以使用
        List<? extends Number> list;
    }

    private void fun4(){
        List<? super Integer> list;
//        fun2(list);   // 不能使用 2.泛型方法调用 （传入的参数必须必须是实例化好的）
//        list = new List<? super Integer>(); // 不能使用 3.泛型类实例创建 ( ? 通配符不能用作实例化，也就是 new 出来的必须是实际参数泛型类).
        Integer[] integers = new Integer[2];
    }


}
