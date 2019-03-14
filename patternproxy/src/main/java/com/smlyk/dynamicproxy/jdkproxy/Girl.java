package com.smlyk.dynamicproxy.jdkproxy;

import com.smlyk.Person;

/**
 * @author yekai
 */
public class Girl implements Person{
    @Override
    public void findLover() {
        System.out.println("高");
        System.out.println("富");
        System.out.println("帅");
    }


}
