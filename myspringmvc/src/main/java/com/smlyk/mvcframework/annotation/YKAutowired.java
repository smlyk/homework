package com.smlyk.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * @author yekai
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface YKAutowired {
    String value() default "";
}
