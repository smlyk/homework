package com.smlyk.decorator.battercake.now;

/**
 * @author yekai
 */
public class BattercakeTest {

    public static void main(String[] args) {

        Battercake battercake = null;

        battercake = new BaseBattercake();

        battercake = new EggDecorator(battercake);

        battercake = new EggDecorator(battercake);

        battercake = new HotdogDecorator(battercake);

        System.out.println(battercake.getMsg() + ",总价：" + battercake.getPrice());


    }

}
