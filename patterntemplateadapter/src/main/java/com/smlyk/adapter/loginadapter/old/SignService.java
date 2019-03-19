package com.smlyk.adapter.loginadapter.old;

import com.smlyk.User;
import com.smlyk.adapter.loginadapter.ResultMsg;

/**
 * @author yekai
 */
public class SignService {

    /**
     * 注册方法
     * @param name
     * @param password
     * @return
     */
    public ResultMsg register(String name, String password){

        return new ResultMsg(200, "注册成功", new User());

    }

    /**
     * 登陆方法
     * @param name
     * @param password
     * @return
     */
    public ResultMsg login(String name, String password){
        return new ResultMsg(200, "登录成功", new User());
    }

}
