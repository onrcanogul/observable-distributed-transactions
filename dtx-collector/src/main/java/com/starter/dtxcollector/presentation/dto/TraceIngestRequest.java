package com.starter.dtxcollector.presentation.dto;

import java.time.Instant;
import java.util.UUID;

public record TraceIngestRequest(
        String traceId,
        String spanId,
        String parentSpanId,
        String serviceName,
        String operationName,
        Instant startedAt,
        Instant endedAt,
        String status,
        String errorMessage,
        UUID projectId
) {
}
