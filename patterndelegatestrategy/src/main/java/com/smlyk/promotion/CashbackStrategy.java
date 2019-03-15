package com.smlyk.promotion;

/**
 * 返现活动
 * @author yekai
 */
public class CashbackStrategy implements PromotionStrategy{
    @Override
    public void doPromotion() {
        System.out.println("买了课程，有返现哟");
    }
}
