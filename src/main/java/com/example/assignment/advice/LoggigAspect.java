package com.example.assignment.advice;

import com.example.assignment.AssignmentApplication;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
@Aspect
public class LoggigAspect {
    private final Logger logger = LoggerFactory.getLogger(AssignmentApplication.class);
    @Pointcut("within(com.example.assignment.serviceLayer.*)")
    public void methods(){};
    @Before("methods()")
    public void logMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        System.out.println("*****************" + methodName);
    }
}
