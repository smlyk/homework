package com.smlyk.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author yekai
 */
@Aspect
@Component
@Slf4j
public class LogAop {

    /**
     * 配置切点
     */
    @Pointcut("execution(* com.smlyk.controller..*(..))")
    public void log(){

    }

    /**
     * 前置通知
     * @param joinPoint
     */
    @Before("log()")
    public void beforeExcution(JoinPoint joinPoint){

        log.info("前置通知开启-------");
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("前置通知结束-------");

    }

    /**
     * 后置返回
     * @param result
     */
    @AfterReturning(returning = "result",pointcut = "log()")
    public void afterReturning(Object result) throws Throwable{
        //处理完请求，返回内容
        log.info("RESPONSE : "+ result);
    }


}
