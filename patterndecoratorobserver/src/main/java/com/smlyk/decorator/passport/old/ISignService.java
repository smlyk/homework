package com.smlyk.decorator.passport.old;

import com.smlyk.decorator.passport.ResultMsg;

/**
 * @author yekai
 */
public interface ISignService {

    ResultMsg register(String name, String password);

    ResultMsg login(String name, String password);

}
