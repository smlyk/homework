package com.smlyk.promotion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yekai
 */
public class PromotionStrategyFactory {

    private static Map<String, PromotionStrategy> PROMOTION_STRATEGY_MAP =  new HashMap<>();

    static {

        PROMOTION_STRATEGY_MAP.put(PromotionKey.COUPON.getKey(), new CouponStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.CASHBACK.getKey(), new CashbackStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.GROUPBUY.getKey(), new GroupBuyStrategy());

    }

    private static final PromotionStrategy EMPTY_PROMOTION = new EmptyStrategy();

    private PromotionStrategyFactory(){}

    public static PromotionStrategy getPromotionStrategy(String promotionKey){

        PromotionStrategy promotionStrategy = PROMOTION_STRATEGY_MAP.get(promotionKey);
        return promotionStrategy == null ? EMPTY_PROMOTION : promotionStrategy;

    }


    enum PromotionKey{

        COUPON("COUPON","优惠券"),

        CASHBACK("CASHBACK","返现"),

        GROUPBUY("GROUPBUY","团购");

        private String key;
        private String value;

        PromotionKey(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
