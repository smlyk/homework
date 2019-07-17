package com.smlyk.seconddemo.current;

import com.smlyk.seconddemo.other.OtherConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author yekai
 */
@Import(OtherConfig.class)
@Configuration
public class SpringConfig {

    @Bean
    public DefaultBean defaultBean(){
        return new DefaultBean();
    }


}
