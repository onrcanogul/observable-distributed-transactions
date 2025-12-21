package com.starter.context;

import java.util.Objects;

public final class TraceContext {
    private final String traceId;
    private final String spanId;
    private final String parentSpanId;
    private final String sagaId;

    public TraceContext(
            String traceId,
            String spanId,
            String parentSpanId,
            String sagaId
    ) {
        this.traceId = Objects.requireNonNull(traceId, "traceId cannot be null");
        this.spanId = Objects.requireNonNull(spanId, "spanId cannot be null");
        this.parentSpanId = parentSpanId;
        this.sagaId = sagaId;
    }

    public String getTraceId() {
        return traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public String getParentSpanId() {
        return parentSpanId;
    }

    public String getSagaId() {
        return sagaId;
    }

    public boolean isRoot() {
        return parentSpanId == null;
    }
}
