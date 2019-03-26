package com.smlyk.mvcframework.annotation;

import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

/**
 * @author yekai
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@YKResponseBody
public @interface YKRestController {

    String value() default "";

}
