package com.smlyk.pay;

import com.smlyk.pay.payport.PayStrategy;

/**
 * @author yekai
 */
public class PayStrategyTest {

    public static void main(String[] args) {

        Order order = new Order("1", "20190315001", 320);

//        System.out.println(order.pay(PayStrategy.WECHAT_PAY));
        System.out.println(order.pay(PayStrategy.ALI_PAY));
    }
}
