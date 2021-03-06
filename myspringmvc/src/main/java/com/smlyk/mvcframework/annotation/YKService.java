package com.smlyk.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * @author yekai
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface YKService {
    String value() default "";
}
