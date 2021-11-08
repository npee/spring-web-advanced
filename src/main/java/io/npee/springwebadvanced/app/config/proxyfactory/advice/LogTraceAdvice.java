package io.npee.springwebadvanced.app.config.proxyfactory.advice;

import io.npee.springwebadvanced.trace.TraceStatus;
import io.npee.springwebadvanced.trace.prod.LogTrace;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public class LogTraceAdvice implements MethodInterceptor {

    private final LogTrace trace;

    public LogTraceAdvice(LogTrace trace) {
        this.trace = trace;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TraceStatus status = null;
        try {
            Method method = invocation.getMethod();
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = trace.begin(message);

            Object result = invocation.proceed();

            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
