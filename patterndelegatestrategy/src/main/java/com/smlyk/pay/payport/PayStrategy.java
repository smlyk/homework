package com.smlyk.pay.payport;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付策略管理
 * @author yekai
 */
public class PayStrategy {

    public static final String ALI_PAY = "AliPay";
    public static final String JD_PAY = "JDPay";
    public static final String UNION_PAY = "UnionPay";
    public static final String WECHAT_PAY = "WechatPay";
    public static final String DEFAULT_PAY = ALI_PAY;

    private static Map<String, Payment> PAY_STRATEGY = new HashMap<>();

    static {
        PAY_STRATEGY.put(ALI_PAY, new AliPay());
        PAY_STRATEGY.put(JD_PAY, new JDPay());
        PAY_STRATEGY.put(UNION_PAY, new UnionPay());
        PAY_STRATEGY.put(WECHAT_PAY, new WechatPay());
    }

    public static Payment getPayment(String paymentKey){

        if (!PAY_STRATEGY.containsKey(paymentKey)){
            return PAY_STRATEGY.get(DEFAULT_PAY);
        }
        return PAY_STRATEGY.get(paymentKey);
    }


}
