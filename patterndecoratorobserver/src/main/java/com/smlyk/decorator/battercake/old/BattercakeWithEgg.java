package com.smlyk.decorator.battercake.old;

/**
 * @author yekai
 */
public class BattercakeWithEgg extends Battercake{

    @Override
    protected String getMsg() {
        return super.getMsg() + "1个鸡蛋";
    }

    /**
     *加一个鸡蛋一块钱
     */
    @Override
    public int getPrice() {
        return super.getPrice() + 1;
    }
}
