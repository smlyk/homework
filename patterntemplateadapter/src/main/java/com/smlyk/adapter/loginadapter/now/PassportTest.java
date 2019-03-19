package com.smlyk.adapter.loginadapter.now;

import com.smlyk.adapter.loginadapter.ResultMsg;

/**
 * @author yekai
 */
public class PassportTest {

    public static void main(String[] args) {

        IPassportForThird passportForThird = new PassportForThirdAdapter();
        ResultMsg resultMsg = passportForThird.loginForQQ("ahfajkf");
        System.out.println(resultMsg);
    }
}
