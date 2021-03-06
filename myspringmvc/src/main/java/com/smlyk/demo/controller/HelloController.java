package com.smlyk.demo.controller;

import com.smlyk.demo.dto.User;
import com.smlyk.demo.service.IHelloService;
import com.smlyk.mvcframework.annotation.YKAutowired;
import com.smlyk.mvcframework.annotation.YKRequestMapping;
import com.smlyk.mvcframework.annotation.YKRequestParam;
import com.smlyk.mvcframework.annotation.YKRestController;

/**
 * @author yekai
 */
@YKRestController
@YKRequestMapping("hello")
public class HelloController {

    @YKAutowired
    private IHelloService helloService;

    @YKRequestMapping("say")
    public String hello(@YKRequestParam("name") String name){

        return helloService.hello(name);
    }

    @YKRequestMapping("user")
    public User user(@YKRequestParam("name") String name, @YKRequestParam Integer age,@YKRequestParam("addr") String addr){

        return new User(name, age, addr);
    }

    @YKRequestMapping("get")
    public User get(){

        return new User("xixi", 22, "默认");
    }

}
