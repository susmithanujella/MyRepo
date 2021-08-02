package com.task2.demo.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TraceableAspect {
	
	private final static Logger logger = LoggerFactory.getLogger(TraceableAspect.class);
 
    @Around("@annotation(LogMethodParam)")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
 
    	logger.info("Input Parameters for method : " + joinPoint.getSignature() + ":");
        //System.out.println("Input Parameters for method : " + joinPoint.getSignature() + ":");
    	
        for(Object obj : joinPoint.getArgs()) {
        	logger.info(obj.toString());
        	//System.out.println(obj);
        }
        
        Object result = joinPoint.proceed();
        return result;
    }
 
}