package com.smlyk.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yekai
 */
@Configuration
@EnableConfigurationProperties(CustomerProperties.class)
public class PropertiesAutoConfiguration {
}
