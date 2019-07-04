package com.smyk;

import java.lang.reflect.Proxy;

/**
 * @author yekai
 */
public class RpcClientProxy {

    //生成代理类
    public <T> T clientProxy(final Class<T> interfaceCls, final String host, final int port){

        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class<?>[]{interfaceCls},
                new RemoteInvocationHandler(host,port));
    }

}
