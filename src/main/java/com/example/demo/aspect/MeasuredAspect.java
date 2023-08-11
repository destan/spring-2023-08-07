package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static com.example.demo.aspect.MeasureUnit.MILLISECOND;

@Slf4j
@Aspect
@Component
public class MeasuredAspect {

    // @Around("@annotation(Measured)")
    @Around("execution(public * *(..)) && @annotation(Measured)")
    public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Measured measuredAnnotation = method.getAnnotation(Measured.class);
        MeasureUnit unit = measuredAnnotation.value();

        long startTime = unit == MILLISECOND ? System.currentTimeMillis() : System.nanoTime();
        Object returnValue = joinPoint.proceed();
        long finishTime = unit == MILLISECOND ? System.currentTimeMillis() : System.nanoTime();

        log.info("Method ({}) execution took {}{}", joinPoint.getSignature(), finishTime-startTime, unit == MILLISECOND ? "ms" : "ns");

        if (returnValue instanceof String returnStr) {
            return returnStr + " this part is from aspect";
        }

        return returnValue;
    }

}
