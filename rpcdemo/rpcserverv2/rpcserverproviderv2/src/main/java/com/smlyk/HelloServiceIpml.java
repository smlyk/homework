package com.smlyk;

import com.smyk.IHelloService;
import com.smyk.User;

/**
 * @author yekai
 */
@RpcService(value = IHelloService.class,version = "v1.0")
public class HelloServiceIpml implements IHelloService{
    @Override
    public String sayHello(String content) {
        System.out.println("【v1.0】 request in sayHello: " + content);
        return "【v1.0】 Say Hello: " + content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("【V1.0】request in saveUser: "+ user);
        return "【V1.0】SUCCESS";
    }
}
