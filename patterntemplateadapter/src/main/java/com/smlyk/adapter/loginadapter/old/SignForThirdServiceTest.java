package com.smlyk.adapter.loginadapter.old;

import com.smlyk.adapter.loginadapter.ResultMsg;

/**
 * @author yekai
 */
public class SignForThirdServiceTest {

    public static void main(String[] args) {

        SignForThirdService service = new SignForThirdService();

        //不改变原来的代码，也要能够兼容新的需求
        ResultMsg resultMsg = service.loginForQQ("afgafjkjaslgko12");
        System.out.println(resultMsg);
    }
}
