package com.smlyk;

/**
 * @author yekai
 */
public class ClientApp {

    public static void main(String[] args) {

        RpcProxyClient rpcProxyClient = new RpcProxyClient();

        //代理类
        IHelloService iHelloService = rpcProxyClient.clientProxy(IHelloService.class, "127.0.0.1", 8088);

        String result = iHelloService.sayHello("Tom");
        System.out.println(result);
    }

}
