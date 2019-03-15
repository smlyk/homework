package com.smlyk.promotion;

/**
 * @author yekai
 */
public class PromotionActivityTest {

    public static void main(String[] args) {
//        //618活动
//        PromotionActivity activity618 = new PromotionActivity(new CouponStrategy());
//        activity618.extcute();
//
//        //1111活动
//        PromotionActivity activity1111 = new PromotionActivity(new CashbackStrategy());
//        activity1111.extcute();



//        PromotionActivity promotionActivity = null;
//        String promotionKey = "COUPON";
//        if ("COUPON".equals(promotionKey)){
//            promotionActivity = new PromotionActivity(new CouponStrategy());
//        }else if ("CASHBACK".equals(promotionKey)){
//            promotionActivity = new PromotionActivity(new CashbackStrategy());
//        }//....
//        promotionActivity.extcute();


        String promotionKey = PromotionStrategyFactory.PromotionKey.GROUPBUY.getKey();
        PromotionActivity promotionActivity = new PromotionActivity(
                PromotionStrategyFactory.getPromotionStrategy(promotionKey));
        promotionActivity.extcute();
    }

}
