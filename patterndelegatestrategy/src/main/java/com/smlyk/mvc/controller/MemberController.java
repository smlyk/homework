package com.smlyk.mvc.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yekai
 */
public class MemberController {

        public void getMemberById(String mid, HttpServletResponse response) throws IOException {

            System.out.println("getMemberById调用了" + mid);
            response.getWriter().write("getMemberById调用了" + mid);
        }
}
