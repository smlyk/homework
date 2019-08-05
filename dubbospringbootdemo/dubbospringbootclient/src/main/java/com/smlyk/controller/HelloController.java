package com.smlyk.controller;

import com.smlyk.IHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yekai
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    @Reference
    private IHelloService helloService;

    @GetMapping("say")
    public String say(@RequestParam String name){

        return helloService.sayHello(name);
    }

}
