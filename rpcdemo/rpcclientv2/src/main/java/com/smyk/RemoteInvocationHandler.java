package com.smyk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yekai
 */
public class RemoteInvocationHandler implements InvocationHandler{

    private String host;

    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //请求会进入这里
        System.out.println("request begin...");

        //请求数据的包装
        RpcRequest request = new RpcRequest();
        if ("java.lang.Object".equals(method.getDeclaringClass().getName())){
            return null;
        }
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);

        //远程通信
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
        return rpcNetTransport.send(request);
    }
}
