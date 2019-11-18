package com.testing.logging.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("(execution(* com.testing.api.controller..*(..)))")
    public Object log(ProceedingJoinPoint jp) {
        Object result = null;
        final long start = System.currentTimeMillis();
        try {
            result = jp.proceed();
            long executionTime = System.currentTimeMillis() - start;
            LOGGER.info("{} {} took {} milliseconds",
                    logString(result), jp.getSignature().toShortString(), executionTime);

        } catch (final Throwable ex) {
            final StackTraceElement[] traces = ex.getStackTrace();

            LOGGER.info("{} threw {} took {} milliseconds",
                    jp.getSignature().toShortString(), ex.getClass().getSimpleName(),
                    (System.currentTimeMillis() - start));
        }
        return result;
    }

    private String logString(Object result) {
        return String.format("The result was: %s", result != null ? result.toString() : "NULL");
    }
}
