package com.smlyk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yekai
 */
@Configuration
@ComponentScan(basePackages = "com.smlyk")
public class SpringConfig {

    @Bean
    public YKRpcServer ykRpcServer(){
        return new YKRpcServer(8080);
    }

}
