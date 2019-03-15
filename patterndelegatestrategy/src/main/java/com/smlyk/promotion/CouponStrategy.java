package com.smlyk.promotion;

/**
 * 优惠券
 * @author yekai
 */
public class CouponStrategy implements PromotionStrategy{
    @Override
    public void doPromotion() {
        System.out.println("领取优惠券，课程的价格可以打折哟");
    }
}
