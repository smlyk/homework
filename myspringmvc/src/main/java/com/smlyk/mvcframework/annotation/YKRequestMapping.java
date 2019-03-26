package com.smlyk.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * @author yekai
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface YKRequestMapping {
    String value() default "";
}
