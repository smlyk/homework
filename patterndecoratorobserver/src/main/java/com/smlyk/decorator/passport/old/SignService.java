package com.smlyk.decorator.passport.old;

import com.smlyk.decorator.passport.ResultMsg;

/**
 * @author yekai
 */
public class SignService implements ISignService{
    @Override
    public ResultMsg register(String name, String password) {
        return new ResultMsg(200,"注册成功", name);
    }

    @Override
    public ResultMsg login(String name, String password) {
        return new ResultMsg(200, "登录成功", name);
    }
}
