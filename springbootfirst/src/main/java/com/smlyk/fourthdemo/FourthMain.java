package com.smlyk.fourthdemo;

import com.smyk.YKCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yekai
 */
@SpringBootApplication
public class FourthMain {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(FourthMain.class, args);


        System.out.println(applicationContext.getBean(YKCore.class).study());
    }

}
