package com.smlyk.controller;

import com.smlyk.SentinelService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yekai
 */
@RestController
@RequestMapping("sentinel")
public class SentinelWebController {

    @Reference
    private SentinelService sentinelService;

    @GetMapping("say")
    public String sayHello(@RequestParam String name){

        String s = sentinelService.sayHello(name);

        return s;
    }

}
