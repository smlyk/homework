package com.smlyk.decorator.battercake.now;

/**
 * 扩展套餐的抽象装饰者
 * @author yekai
 */
public abstract class BattercakeDecorator extends Battercake{

    private Battercake battercake;

    public BattercakeDecorator(Battercake battercake){
        this.battercake = battercake;
    }

    protected abstract void doSomethig();

    @Override
    protected String getMsg() {
        return this.battercake.getMsg();
    }

    @Override
    protected int getPrice() {
        return this.battercake.getPrice();
    }
}
