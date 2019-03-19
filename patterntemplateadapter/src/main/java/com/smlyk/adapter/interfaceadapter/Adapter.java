package com.smlyk.adapter.interfaceadapter;

/**
 * @author yekai
 */
public abstract class Adapter implements InterfaceA{

    @Override
    public void methodA() {
        System.out.println("方法A调用了");
    }

    @Override
    public void methodB() {
        System.out.println("方法B调用了");
    }

    @Override
    public void methodC() {
        System.out.println("方法C调用了");
    }


}
