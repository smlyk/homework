package com.smlyk.autoconfiguration;

import com.smlyk.CuratorZkTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yekai
 */
@Configuration
@EnableConfigurationProperties(CuratorFrameworkConfigProperties.class)
public class CuratorZkAutoConfiguration {

    @Bean
    public CuratorZkTemplate curatorZkTemplate(CuratorFrameworkConfigProperties properties) throws Exception {
        return new CuratorZkTemplate(properties);
    }

}
