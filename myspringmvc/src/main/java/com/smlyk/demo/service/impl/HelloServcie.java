package com.smlyk.demo.service.impl;

import com.smlyk.demo.service.IHelloService;
import com.smlyk.mvcframework.annotation.YKService;

/**
 * @author yekai
 */
@YKService
public class HelloServcie implements IHelloService{
    @Override
    public String hello(String name) {
        return "Hello "+ name;
    }
}
