package com.starter.dtxspring.aop;

import com.starter.context.TraceContext;
import com.starter.context.TraceContextHolder;
import com.starter.dtxspring.annotation.Traceable;
import com.starter.dtxspring.event.TraceStepEvent;
import com.starter.dtxspring.publisher.TraceEventPublisher;
import com.starter.dtxspring.support.SpanLifecycleManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.time.Instant;

@Aspect
public class TraceAspect {

    private final SpanLifecycleManager spanManager;
    private final TraceEventPublisher eventPublisher;
    private final String serviceName;

    public TraceAspect(SpanLifecycleManager spanManager, TraceEventPublisher eventPublisher, String serviceName) {
        this.spanManager = spanManager;
        this.eventPublisher = eventPublisher;
        this.serviceName = serviceName;
    }

    @Around("@annotation(traceable) && !@annotation(com.starter.dtxspring.annotation.NoTrace)")
    public Object traceMethod(
            ProceedingJoinPoint pjp,
            Traceable traceable
    ) throws Throwable {

        MethodSignature signature = (MethodSignature) pjp.getSignature();

        String operationName = traceable.name().isEmpty()
                ? signature.getDeclaringType().getSimpleName() + "." + signature.getName()
                : traceable.name();

        TraceContext previousContext = null;
        TraceContext currentContext;
        Instant startedAt = Instant.now();
        String status = "OK";
        String errorMessage = null;

        try {
            previousContext = spanManager.startSpan(operationName);
            currentContext = TraceContextHolder.get();

            return pjp.proceed();
        } catch (Throwable ex) {
            status = "ERROR";
            errorMessage = ex.getClass().getSimpleName() + ": " + ex.getMessage();
            throw ex;
        } finally {
            Instant endedAt = Instant.now();

            currentContext = TraceContextHolder.get();

            if (currentContext != null) {
                TraceStepEvent event = new TraceStepEvent();
                event.setTraceId(currentContext.getTraceId());
                event.setSpanId(currentContext.getSpanId());
                event.setParentSpanId(currentContext.getParentSpanId());

                event.setServiceName(serviceName);
                event.setOperationName(operationName);

                event.setStartedAt(startedAt);
                event.setEndedAt(endedAt);

                event.setStatus(status);
                event.setErrorMessage(errorMessage);

                eventPublisher.publish(event);
            }

            spanManager.endSpan(previousContext);
        }
    }
}
