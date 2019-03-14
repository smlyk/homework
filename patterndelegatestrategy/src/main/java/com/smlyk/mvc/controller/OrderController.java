package com.smlyk.mvc.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yekai
 */
public class OrderController {

    public void getOrderById(String mid, HttpServletResponse response) throws IOException {
        System.out.println("getOrderById调用了" + mid);

        response.getWriter().write("getOrderById调用了" + mid);
    }
}
