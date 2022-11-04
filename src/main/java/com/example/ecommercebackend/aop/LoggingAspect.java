package com.example.ecommercebackend.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.mapping.Join;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Pointcut(value = "execution(* com.example.ecommercebackend.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut(value = "execution(* com.example.ecommercebackend.repository.*.*(..))")
    private void forRepositoryPackage(){}

    @Pointcut(value = "execution(* com.example.ecommercebackend.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut(value = "forControllerPackage() || forRepositoryPackage() || forServicePackage()")
    private void forAppFlow(){}

    @Before(value = "forAppFlow()")
    public void logBeforeEachMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Method " + methodName + " called");

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info("Method argument: " + arg);
        }
    }

    @AfterReturning(value = "forAppFlow()", returning = "result")
    public void logAfterEachMethodReturn(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();

        log.info("Result from method: " + methodName + " is: " + result);
    }



}
