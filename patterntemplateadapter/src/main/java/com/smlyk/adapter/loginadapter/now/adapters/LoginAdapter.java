package com.smlyk.adapter.loginadapter.now.adapters;

import com.smlyk.adapter.loginadapter.ResultMsg;

/**
 * @author yekai
 */
public interface LoginAdapter {

    /**
     * 是否支持
     * @param adapter
     * @return
     */
    boolean support(Object adapter);

    /**
     * 登录方法
     * @param id
     * @param adapter
     * @return
     */
    ResultMsg login(String id, Object adapter);
}
