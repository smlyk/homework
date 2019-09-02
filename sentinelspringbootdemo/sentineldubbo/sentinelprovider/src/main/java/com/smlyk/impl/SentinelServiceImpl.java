package com.smlyk.impl;

import com.smlyk.SentinelService;
import org.apache.dubbo.config.annotation.Service;

import java.time.LocalDateTime;

/**
 * @author yekai
 */
//注解，把当前服务发布成dubbo服务
@Service
public class SentinelServiceImpl implements SentinelService{

    @Override
    public String sayHello(String name) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello "+ name + " : "+ LocalDateTime.now();
    }
}
