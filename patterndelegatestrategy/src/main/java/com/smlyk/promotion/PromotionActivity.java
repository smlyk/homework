package com.smlyk.promotion;

/**
 * 促销活动
 * @author yekai
 */
public class PromotionActivity {

    private PromotionStrategy promotionStrategy;

    public PromotionActivity(PromotionStrategy promotionStrategy){
        this.promotionStrategy = promotionStrategy;
    }

    public void extcute(){
        promotionStrategy.doPromotion();
    }

}
