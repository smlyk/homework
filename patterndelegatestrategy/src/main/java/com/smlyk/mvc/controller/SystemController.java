package com.smlyk.mvc.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yekai
 */
public class SystemController {

    public void logout(String mid, HttpServletResponse response) throws IOException {
        System.out.println("logout调用了" + mid);
        response.getWriter().write("logout调用了" + mid);
    }
}
