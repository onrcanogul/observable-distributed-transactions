package com.starter.dtxspring.support;

import com.starter.context.TraceContext;
import com.starter.context.TraceContextFactory;
import com.starter.context.TraceContextHolder;

public class SpanLifecycleManager {

    private final TraceContextFactory factory;

    public SpanLifecycleManager(TraceContextFactory factory) {
        this.factory = factory;
    }

    public TraceContext startSpan(String spanName) {
        TraceContext context = TraceContextHolder.get();

        if (context == null) {
            return null;
        }

        TraceContext child = factory.createChild(
                context.getTraceId(),
                context.getSpanId(),
                context.getSagaId()
        );

        TraceContextHolder.set(child);
        return context; // return previous context for restoration
    }

    public void endSpan(TraceContext previousContext) {
        if (previousContext != null) {
            TraceContextHolder.set(previousContext);
        }
    }
}
