package com.smlyk.decorator.passport.now;

import com.smlyk.decorator.passport.ResultMsg;
import com.smlyk.decorator.passport.old.ISignService;

/**
 * @author yekai
 */
public class SignForThirdService implements ISignForThirdService{

    private ISignService signService;

    public SignForThirdService(ISignService signService){
        this.signService = signService;
    }

    @Override
    public ResultMsg loginForQQ(String id) {
        return new ResultMsg(200,"QQ登录成功", id);
    }

    @Override
    public ResultMsg loginForWechat(String id) {
        return null;
    }

    @Override
    public ResultMsg loginForToken(String token) {
        return null;
    }

    @Override
    public ResultMsg loginForTelphone(String telphone, String code) {
        return null;
    }

    @Override
    public ResultMsg loginForRegist(String username, String passport) {
        return null;
    }

    @Override
    public ResultMsg register(String name, String password) {
        return this.signService.register(name, password);
    }

    @Override
    public ResultMsg login(String name, String password) {
        return this.signService.login(name, password);
    }
}
