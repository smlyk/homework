package com.smlyk.decorator.passport.now;

import com.smlyk.decorator.passport.ResultMsg;
import com.smlyk.decorator.passport.old.SignService;

/**
 * @author yekai
 */
public class DecoratorTest {

    public static void main(String[] args) {

        ISignForThirdService signForThirdService = new SignForThirdService(new SignService());

        ResultMsg login = signForThirdService.login("zhangsan", "123456");
        System.out.println(login);

        ResultMsg loginForQQ = signForThirdService.loginForQQ("afgjfalfafa");
        System.out.println(loginForQQ);
    }
}
