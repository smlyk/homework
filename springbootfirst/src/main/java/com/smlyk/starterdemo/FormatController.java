package com.smlyk.starterdemo;

import com.smlyk.HelloFormatTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yekai
 */
@RestController
public class FormatController {

    @Autowired
    private HelloFormatTemplate helloFormatTemplate;

    @GetMapping("format")
    public String format(){

        User user = new User("zhangsan", 22);

        return helloFormatTemplate.doFormat(user);
    }

}
