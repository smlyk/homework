package com.smlyk.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * @author yekai
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface YKRequestParam {
    String value() default "";
}
