package com.smlyk.firstdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yekai
 */
@ComponentScan(basePackages = "com.smlyk")
public class ConfigurationMain {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigurationMain.class);

        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println("name = "+ name);
        }


    }


}
