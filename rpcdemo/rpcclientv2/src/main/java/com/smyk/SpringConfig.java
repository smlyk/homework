package com.smyk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yekai
 */
@Configuration
public class SpringConfig {

    @Bean
    public RpcClientProxy rpcClientProxy(){
        return new RpcClientProxy();
    }


}
