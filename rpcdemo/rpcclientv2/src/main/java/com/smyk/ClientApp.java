package com.smyk;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yekai
 */
public class ClientApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        RpcClientProxy clientProxy = applicationContext.getBean(RpcClientProxy.class);

        IPaymentService iPaymentService = clientProxy.clientProxy(IPaymentService.class, "127.0.0.1", 8080);
        System.out.println(iPaymentService.getClass().getName());
        iPaymentService.doPay();

    }

}
