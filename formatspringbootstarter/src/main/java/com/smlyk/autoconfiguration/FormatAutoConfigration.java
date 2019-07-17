package com.smlyk.autoconfiguration;

import com.smlyk.formator.FormatProcessor;
import com.smlyk.formator.JsonFormatProcessor;
import com.smlyk.formator.StringFormatProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author yekai
 */
@Configuration
public class FormatAutoConfigration {

    @ConditionalOnMissingClass("com.alibaba.fastjson.JSON")
    @Bean
    @Primary
    public FormatProcessor stringFormat(){
        return new StringFormatProcessor();
    }


    @ConditionalOnClass(name = "com.alibaba.fastjson.JSON")
    @Bean
    public JsonFormatProcessor jsonFormat(){
        return new JsonFormatProcessor();
    }


}
