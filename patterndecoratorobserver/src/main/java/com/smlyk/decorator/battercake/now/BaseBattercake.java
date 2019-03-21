package com.smlyk.decorator.battercake.now;

/**
 * 煎饼基础套餐
 * @author yekai
 */
public class BaseBattercake extends Battercake{
    @Override
    protected String getMsg() {
        return "煎饼";
    }

    @Override
    protected int getPrice() {
        return 5;
    }
}
