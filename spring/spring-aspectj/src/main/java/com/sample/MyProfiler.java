package com.sample;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyProfiler {

    @Pointcut("execution(* com.sample.*.*(..))")
    public void businessMethods() { }

    @Around("businessMethods()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {

        long start = System.currentTimeMillis();
        System.out.println("Going to call the method."+pjp.getSignature().getName());
        Object output = pjp.proceed();
        System.out.println("Method execution completed."+pjp.getSignature().getName());
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println(pjp.getSignature().getName() + " execution time: " + elapsedTime + " milliseconds.");
        return output;
    }

}