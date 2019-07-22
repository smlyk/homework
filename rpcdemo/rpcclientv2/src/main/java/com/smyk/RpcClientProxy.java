package com.smyk;

import com.smyk.discovery.IServiceDiscovery;
import com.smyk.discovery.ServiceDiscoveryWithZk;

import java.lang.reflect.Proxy;

/**
 * @author yekai
 */
public class RpcClientProxy {

    private IServiceDiscovery serviceDiscovery=new ServiceDiscoveryWithZk();

    //生成代理类
    public <T> T clientProxy(final Class<T> interfaceCls, String version){

        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class<?>[]{interfaceCls},
                new RemoteInvocationHandler(serviceDiscovery,version));
    }

}
