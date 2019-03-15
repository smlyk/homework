package com.smlyk.pay.payport;

/**
 * @author yekai
 */
public class WechatPay extends Payment{
    @Override
    public String getName() {
        return "微信支付";
    }

    @Override
    protected double queryBalance(String uid) {
        return 220;
    }
}
