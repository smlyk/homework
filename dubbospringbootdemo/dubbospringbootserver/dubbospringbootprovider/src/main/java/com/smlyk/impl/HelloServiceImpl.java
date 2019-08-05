package com.smlyk.impl;

import com.smlyk.IHelloService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author yekai
 */
@Service
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
