package io.npee.springwebadvanced.app.config.aop.aspect;

import io.npee.springwebadvanced.trace.TraceStatus;
import io.npee.springwebadvanced.trace.prod.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

@Slf4j
@Aspect
public class LogTraceAspect {

    private final LogTrace trace;

    public LogTraceAspect(LogTrace trace) {
        this.trace = trace;
    }

    @Around("execution(* io.npee.springwebadvanced.app.v*..*(..)) " +
            "&& !execution(* io.npee.springwebadvanced.app..noLog(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;
        try {
            // Method method = invocation.getMethod();
            // String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            String message = joinPoint.getSignature().toShortString();
            status = trace.begin(message);

            // Object result = invocation.proceed();
            Object result = joinPoint.proceed();

            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
