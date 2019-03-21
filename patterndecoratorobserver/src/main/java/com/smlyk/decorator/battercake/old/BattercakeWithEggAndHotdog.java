package com.smlyk.decorator.battercake.old;

/**
 * @author yekai
 */
public class BattercakeWithEggAndHotdog extends BattercakeWithEgg{

    @Override
    protected String getMsg() {
        return super.getMsg() + "1根香肠";
    }

    /**
     * 加一根香肠2块
     * @return
     */
    @Override
    public int getPrice() {
        return super.getPrice() + 2;
    }
}
