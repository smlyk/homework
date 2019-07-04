package com.smlyk;

/**
 * @author yekai
 */
public class ServerApp {

    public static void main(String[] args) {

        IHelloService helloService = new HelloServiceImpl();

        RpcProxyServer proxyServer = new RpcProxyServer();
        proxyServer.publisher(helloService, 8088);
    }

}
