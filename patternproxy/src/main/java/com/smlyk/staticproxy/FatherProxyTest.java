package com.smlyk.staticproxy;

/**
 * @author yekai
 */
public class FatherProxyTest {

    public static void main(String[] args) {

        //只能帮儿子找对象
        //不能帮陌生人
        Father father = new Father(new Son());
        father.findLover();

    }
}
