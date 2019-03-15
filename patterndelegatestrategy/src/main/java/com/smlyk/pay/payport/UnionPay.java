package com.smlyk.pay.payport;

/**
 * @author yekai
 */
public class UnionPay extends Payment{
    @Override
    public String getName() {
        return "银联支付";
    }

    @Override
    protected double queryBalance(String uid) {
        return 400;
    }
}
