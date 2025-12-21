package com.starter.dtxspring.aop;

import com.starter.context.TraceContext;
import com.starter.dtxspring.annotation.Traceable;
import com.starter.dtxspring.support.SpanLifecycleManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class TraceAspect {

    private final SpanLifecycleManager spanManager;

    public TraceAspect(SpanLifecycleManager spanManager) {
        this.spanManager = spanManager;
    }

    @Around("@annotation(traceable) && !@annotation(com.starter.dtxspring.annotation.NoTrace)")
    public Object traceMethod(
            ProceedingJoinPoint pjp,
            Traceable traceable
    ) throws Throwable {

        MethodSignature signature = (MethodSignature) pjp.getSignature();

        String spanName = traceable.name().isBlank()
                ? signature.getDeclaringType().getSimpleName() + "." + signature.getName()
                : traceable.name();

        TraceContext previous = spanManager.startSpan(spanName);

        try {
            return pjp.proceed();
        } catch (Throwable ex) {
            throw ex;
        } finally {
            spanManager.endSpan(previous);
        }
    }
}
