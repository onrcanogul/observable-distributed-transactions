package com.starter.dtxspring.support;

import com.starter.context.TraceContext;
import com.starter.context.TraceContextFactory;
import com.starter.propagation.TraceHeaders;
import jakarta.servlet.http.HttpServletRequest;

public class TraceContextExtractor {

    private final TraceContextFactory factory;

    public TraceContextExtractor(TraceContextFactory factory) {
        this.factory = factory;
    }

    public TraceContext extract(HttpServletRequest request) {
        String traceId = request.getHeader(TraceHeaders.TRACE_ID);
        String parentSpanId = request.getHeader(TraceHeaders.SPAN_ID);
        String sagaId = request.getHeader(TraceHeaders.SAGA_ID);

        if (traceId == null || traceId.isEmpty()) {
            return factory.createRoot(sagaId);
        }

        return factory.createChild(traceId, parentSpanId, sagaId);
    }


}
