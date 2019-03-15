package com.smlyk.promotion;

/**
 * 拼团优惠
 * @author yekai
 */
public class GroupBuyStrategy implements PromotionStrategy{
    @Override
    public void doPromotion() {
        System.out.println("拼团，满20人成团，全团享受团购价");
    }
}
