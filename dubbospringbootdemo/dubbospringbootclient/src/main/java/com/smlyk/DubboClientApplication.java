package com.smlyk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author yekai
 */
@SpringBootApplication
public class DubboClientApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {

        SpringApplication.run(DubboClientApplication.class, args);

    }

}
