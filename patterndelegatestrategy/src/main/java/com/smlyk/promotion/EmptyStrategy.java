package com.smlyk.promotion;

/**
 * 无优惠
 * @author yekai
 */
public class EmptyStrategy implements PromotionStrategy{
    @Override
    public void doPromotion() {
        System.out.println("无促销活动");
    }
}
