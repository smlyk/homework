package com.smlyk.demo.controller;

import com.smlyk.mvcframework.annotation.YKController;
import com.smlyk.mvcframework.annotation.YKRequestMapping;
import com.smlyk.mvcframework.annotation.YKRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yekai
 */
@YKController
@YKRequestMapping("demo")
public class DemoController {

    @YKRequestMapping("query")
    public void query(HttpServletRequest req, HttpServletResponse resp,
                      @YKRequestParam("name") String name){

        String result = "My name is " + name;
        try {
            resp.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @YKRequestMapping("/add")
    public void add(HttpServletRequest req, HttpServletResponse resp,
                    @YKRequestParam("a") Integer a, @YKRequestParam("b") Integer b){
        try {
            resp.getWriter().write(a + "+" + b + "=" + (a + b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
