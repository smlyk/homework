package com.smyk;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yekai
 */
public class ClientApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        RpcClientProxy clientProxy = applicationContext.getBean(RpcClientProxy.class);

        IPaymentService iPaymentService = clientProxy.clientProxy(IPaymentService.class, null);
        System.out.println(iPaymentService.getClass().getName());
        iPaymentService.doPay();

        IHelloService iHelloService = clientProxy.clientProxy(IHelloService.class, "v2.0");
        iHelloService.sayHello("Mic");

    }

}
