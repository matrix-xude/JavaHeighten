package basic.entity;

import basic.domain.DescObtain;
import basic.domain.IntObtain;

@DescObtain(desc = "我是父类")
@IntObtain(1)
public class InheritedEntityFather {

    @DescObtain(desc = "父类的Field")
    public int a;

    @DescObtain(desc = "方法，父类上")
    public void fun1() {
    }
}
