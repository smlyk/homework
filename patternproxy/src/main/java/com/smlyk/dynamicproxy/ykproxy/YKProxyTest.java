package com.smlyk.dynamicproxy.ykproxy;

import com.smlyk.Person;
import com.smlyk.dynamicproxy.jdkproxy.Girl;

/**
 * @author yekai
 */
public class YKProxyTest {

    public static void main(String[] args) {

        Person obj = (Person) new YKMeipo().getInstance(new Girl());
        System.out.println(obj.getClass());

        obj.findLover();

    }

}
