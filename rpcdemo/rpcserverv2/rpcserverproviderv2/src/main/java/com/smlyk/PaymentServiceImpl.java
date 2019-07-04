package com.smlyk;

import com.smyk.IPaymentService;

/**
 * @author yekai
 */
@RpcService(value = IPaymentService.class)
public class PaymentServiceImpl implements IPaymentService{
    @Override
    public void doPay() {
        System.out.println("执行onPay方法...");
    }
}
