package com.smlyk.adapter.loginadapter.now.adapters;

import com.smlyk.adapter.loginadapter.ResultMsg;

/**
 * @author yekai
 */
public class LoginForWechatAdapter implements LoginAdapter{
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForWechatAdapter;
    }

    @Override
    public ResultMsg login(String id, Object adapter) {
        return new ResultMsg(200, "Wechat登录成功", id);
    }
}
