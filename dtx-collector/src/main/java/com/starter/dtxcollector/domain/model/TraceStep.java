package com.starter.dtxcollector.domain.model;

import java.time.Instant;

import java.time.Duration;
import java.util.Objects;

public final class TraceStep {

    private final String traceId;
    private final String spanId;
    private final String parentSpanId;

    private final String serviceName;
    private final String operationName;

    private final Instant startedAt;
    private final Instant endedAt;

    private final String status;
    private final String errorMessage;

    public enum Status {
        OK,
        ERROR
    }

    public TraceStep(
            String traceId,
            String spanId,
            String parentSpanId,
            String serviceName,
            String operationName,
            Instant startedAt,
            Instant endedAt,
            String status,
            String errorMessage
    ) {
        this.traceId = require(traceId, "traceId");
        this.spanId = require(spanId, "spanId");
        this.parentSpanId = parentSpanId;

        this.serviceName = require(serviceName, "serviceName");
        this.operationName = require(operationName, "operationName");

        this.startedAt = require(startedAt, "startedAt");
        this.endedAt = require(endedAt, "endedAt");

        if (endedAt.isBefore(startedAt)) {
            throw new IllegalArgumentException("endedAt cannot be before startedAt");
        }

        this.status = status;
        this.errorMessage = errorMessage;
    }

    // Domain behavior

    public Duration duration() {
        return Duration.between(startedAt, endedAt);
    }

    public boolean isError() {
        return Objects.equals(status, "ERROR");
    }

    // Getters

    public String getTraceId() {
        return traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public String getParentSpanId() {
        return parentSpanId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getOperationName() {
        return operationName;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public Instant getEndedAt() {
        return endedAt;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    // Helper

    private static <T> T require(T value, String name) {
        return Objects.requireNonNull(value, name + " must not be null");
    }
}
