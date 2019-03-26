package com.smlyk.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * @author yekai
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface YKResponseBody {
    boolean required() default true;
}
