package com.starter.propagation;

public final class TraceHeaders {

    public static final String TRACE_ID = "X-Trace-Id";
    public static final String SPAN_ID = "X-Span-Id";
    public static final String SAGA_ID = "X-Saga-Id";

    private TraceHeaders() {}
}
