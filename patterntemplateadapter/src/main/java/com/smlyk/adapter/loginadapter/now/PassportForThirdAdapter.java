package com.smlyk.adapter.loginadapter.now;

import com.smlyk.adapter.loginadapter.ResultMsg;
import com.smlyk.adapter.loginadapter.now.adapters.*;
import com.smlyk.adapter.loginadapter.old.SignService;

/**
 * @author yekai
 */
public class PassportForThirdAdapter extends SignService implements IPassportForThird {
    @Override
    public ResultMsg loginForQQ(String id) {
        return processLogin(id, LoginForQQAdapter.class);
    }

    @Override
    public ResultMsg loginForWechat(String id) {
        return processLogin(id, LoginForWechatAdapter.class);
    }

    @Override
    public ResultMsg loginForToken(String token) {
        return processLogin(token, LoginForTokenAdapter.class);
    }

    @Override
    public ResultMsg loginForTelphone(String telphone, String code) {
        return processLogin(telphone, LoginForTelAdapter.class);
    }

    @Override
    public ResultMsg loginForRegist(String username, String passport) {
        super.register(username,null);
        return super.login(username,null);
    }


    public ResultMsg processLogin(String key, Class<? extends LoginAdapter> clazz){

        try {
            LoginAdapter loginAdapter = clazz.newInstance();
            if (loginAdapter.support(loginAdapter)){
                return loginAdapter.login(key, loginAdapter);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
