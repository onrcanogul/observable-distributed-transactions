package com.starter.context;

import com.starter.factory.SpanIdGenerator;
import com.starter.factory.TraceIdGenerator;

public class TraceContextFactory {

    private final TraceIdGenerator traceIdGenerator;
    private final SpanIdGenerator spanIdGenerator;

    public TraceContextFactory(
            TraceIdGenerator traceIdGenerator,
            SpanIdGenerator spanIdGenerator
    ) {
        this.traceIdGenerator = traceIdGenerator;
        this.spanIdGenerator = spanIdGenerator;
    }

    public TraceContext createRoot(String sagaId) {
        return new TraceContext(
                traceIdGenerator.generate(),
                spanIdGenerator.generate(),
                null,
                sagaId
        );
    }

    public TraceContext createChild(String traceId, String parentSpanId, String sagaId) {
        return new TraceContext(
                traceId,
                spanIdGenerator.generate(),
                parentSpanId,
                sagaId
        );
    }


}
