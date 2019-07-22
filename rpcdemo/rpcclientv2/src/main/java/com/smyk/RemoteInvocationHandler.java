package com.smyk;

import com.smyk.discovery.IServiceDiscovery;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yekai
 */
public class RemoteInvocationHandler implements InvocationHandler{

    private IServiceDiscovery serviceDiscovery;

    private String version;

    public RemoteInvocationHandler(IServiceDiscovery serviceDiscovery, String version) {
      this.serviceDiscovery = serviceDiscovery;
      this.version = version;
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
        request.setVersion(version);
        String serviceName = request.getClassName();
        if(!StringUtils.isEmpty(version)){
            serviceName=serviceName+"_"+version;
        }
        String serviceAddress=serviceDiscovery.discovery(serviceName);

        //远程通信
        RpcNetTransport rpcNetTransport = new RpcNetTransport(serviceAddress);
        return rpcNetTransport.send(request);
    }
}
