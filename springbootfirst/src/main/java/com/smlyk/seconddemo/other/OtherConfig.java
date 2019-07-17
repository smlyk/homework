package com.smlyk.seconddemo.other;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yekai
 */
@Configuration
public class OtherConfig {

    @Bean
    public OtherBean otherBean(){
        return new OtherBean();
    }

}
