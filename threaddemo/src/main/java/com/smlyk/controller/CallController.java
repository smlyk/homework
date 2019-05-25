package com.smlyk.controller;

import com.smlyk.util.JavaCallPython;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yekai
 */
@RestController
@RequestMapping("call")
public class CallController {

    @Autowired
    private JavaCallPython javaCallPython;

    @GetMapping("calculate")
    public String calculate(){

        int result = javaCallPython.calculate();

        return "SUCCESS";
    }

    @GetMapping("calculate2")
    public String calculate2(){

        new Thread(()->javaCallPython.calculate()).start();

        return "SUCCESS";
    }

}
