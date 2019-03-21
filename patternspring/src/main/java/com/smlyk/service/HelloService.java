package com.smlyk.service;

import org.springframework.stereotype.Service;

/**
 * @author yekai
 */
@Service
public class HelloService {

    public String buildRes(String name){

        return "Hello" + name;

    }

}
