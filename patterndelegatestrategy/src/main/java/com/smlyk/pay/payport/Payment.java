package com.smlyk.pay.payport;

import com.smlyk.pay.PayState;

/**
 * 支付渠道抽象
 * @author yekai
 */
public abstract class Payment {

    /**
     * 支付类型
     * @return
     */
    public abstract String getName();

    /**
     * 查询余额
     * @return
     */
    protected abstract double queryBalance(String uid);

    /**
     * 扣款支付
     * @return
     */
    public PayState pay(String uid, double amount){

        if (queryBalance(uid) < amount){
            return new PayState(500, "支付失败", "余额不足");
        }
        return new PayState(200, "支付成功", "支付金额："+ amount);
    }

}
