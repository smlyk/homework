package com.smlyk.decorator.battercake.old;

/**
 * @author yekai
 */
public class BattercakeTest {

    public static void main(String[] args) {

        Battercake battercake = new Battercake();
        System.out.println(battercake.getMsg() + ",总价格："+battercake.getPrice());

        Battercake  battercakeWithEgg= new BattercakeWithEgg();
        System.out.println(battercakeWithEgg.getMsg() + ",总价格："+battercakeWithEgg.getPrice());

        Battercake battercakeWithEggAndHotdog= new BattercakeWithEggAndHotdog();
        System.out.println(battercakeWithEggAndHotdog.getMsg() + ",总价格："+battercakeWithEggAndHotdog.getPrice());

    }

}
