package com.smlyk.dynamicproxy.ykproxy;

import java.lang.reflect.Method;

/**
 * @author yekai
 */
public interface YKInvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
