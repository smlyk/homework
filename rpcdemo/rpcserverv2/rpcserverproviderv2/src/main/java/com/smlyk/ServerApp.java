package com.smlyk;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yekai
 */
public class ServerApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        applicationContext.start();

    }

}
