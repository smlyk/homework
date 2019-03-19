package com.smlyk.adapter.loginadapter.now.adapters;

import com.smlyk.adapter.loginadapter.ResultMsg;

/**
 * @author yekai
 */
public class LoginForTelAdapter implements LoginAdapter{
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForTelAdapter;
    }

    @Override
    public ResultMsg login(String id, Object adapter) {
        return new ResultMsg(200, "Tel登录成功", id);
    }
}
