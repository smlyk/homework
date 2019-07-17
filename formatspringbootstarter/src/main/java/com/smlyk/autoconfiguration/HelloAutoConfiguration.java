package com.smlyk.autoconfiguration;

import com.smlyk.HelloFormatTemplate;
import com.smlyk.formator.FormatProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author yekai
 */
@Import(FormatAutoConfigration.class)
@EnableConfigurationProperties(HelloProperties.class)
@Configuration
public class HelloAutoConfiguration {

    @Bean
    public HelloFormatTemplate helloFormatTemplate(HelloProperties helloProperties, FormatProcessor formatProcessor){
        return new HelloFormatTemplate(helloProperties, formatProcessor);
    }

}
