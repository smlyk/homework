package com.smlyk.thirddemo;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author yekai
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CacheImportSelector.class, LoggerDefinitionRegistrar.class})
public @interface EnableDefineService {

    Class<?>[] exclude() default {};

}
