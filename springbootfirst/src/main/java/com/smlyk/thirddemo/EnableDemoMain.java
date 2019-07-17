package com.smlyk.thirddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yekai
 */
@SpringBootApplication
@EnableDefineService(exclude = {CacheService.class})
public class EnableDemoMain {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(EnableDemoMain.class, args);

        System.out.println(applicationContext.getBean(LoggerService.class));
        System.out.println(applicationContext.getBean(CacheService.class));

    }

}
