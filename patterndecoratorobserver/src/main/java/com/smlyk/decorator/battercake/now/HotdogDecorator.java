package com.smlyk.decorator.battercake.now;

/**
 * @author yekai
 */
public class HotdogDecorator extends BattercakeDecorator{
    public HotdogDecorator(Battercake battercake) {
        super(battercake);
    }

    @Override
    protected void doSomethig() {

    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "+1根香肠";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() +2;
    }
}
