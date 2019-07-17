package com.smlyk.seconddemo.current;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yekai
 */
public class SecondMain {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for(String name: definitionNames){
            System.out.println("name = " + name);
        }


    }
}
