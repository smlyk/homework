package com.smlyk.pay;

/**
 * 支付以后的状态
 * @author yekai
 */
public class PayState {

    private int code;
    private Object data;
    private String msg;

    public PayState(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "支付状态：" + code +"/" + data + ",交易详情："+ msg;
    }
}
