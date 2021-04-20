package basic.entity;

import basic.domain.DescObtain;
import basic.domain.IntObtain;

@DescObtain(desc = "父类的类上")
public class ReflectBaseEntity {


    protected int fatherA;
    @IntObtain(51)
    public String fatherStr;

    public void sub(int b) {

    }

    private String fun2() {
        return "abc";
    }
}
