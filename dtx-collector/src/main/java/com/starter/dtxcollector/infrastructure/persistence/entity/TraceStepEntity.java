package com.starter.dtxcollector.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(
        name = "trace_steps",
        indexes = {
                @Index(name = "idx_trace_id", columnList = "trace_id"),
                @Index(name = "idx_span_id", columnList = "span_id"),
                @Index(name = "idx_parent_span_id", columnList = "parent_span_id"),
                @Index(name = "idx_started_at", columnList = "started_at")
        }
)
public class TraceStepEntity {

    // ---------------------------------------------------------------------
    // Surrogate primary key
    // We do NOT use spanId as PK to allow future flexibility
    // ---------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ---------------------------------------------------------------------
    // Trace identifiers
    // ---------------------------------------------------------------------

    @Column(name = "trace_id", nullable = false, length = 64)
    private String traceId;

    @Column(name = "span_id", nullable = false, length = 64)
    private String spanId;

    @Column(name = "parent_span_id", length = 64)
    private String parentSpanId;

    // ---------------------------------------------------------------------
    // Context information
    // ---------------------------------------------------------------------

    @Column(name = "service_name", nullable = false, length = 128)
    private String serviceName;

    @Column(name = "operation_name", nullable = false, length = 256)
    private String operationName;

    // ---------------------------------------------------------------------
    // Timing information
    // ---------------------------------------------------------------------

    @Column(name = "started_at", nullable = false)
    private Instant startedAt;

    @Column(name = "ended_at", nullable = false)
    private Instant endedAt;

    // ---------------------------------------------------------------------
    // Execution result
    // ---------------------------------------------------------------------

    @Column(name = "status", nullable = false, length = 16)
    private String status; // OK / ERROR

    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

    // ---------------------------------------------------------------------
    // JPA requires a no-arg constructor
    // ---------------------------------------------------------------------
    protected TraceStepEntity() {
    }

    // Getters (no setters on purpose)
    // Entity is mutated only via constructor/factory

    public Long getId() {
        return id;
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

    // Static factory for controlled creation

    public static TraceStepEntity of(
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
        TraceStepEntity entity = new TraceStepEntity();
        entity.traceId = traceId;
        entity.spanId = spanId;
        entity.parentSpanId = parentSpanId;
        entity.serviceName = serviceName;
        entity.operationName = operationName;
        entity.startedAt = startedAt;
        entity.endedAt = endedAt;
        entity.status = status;
        entity.errorMessage = errorMessage;
        return entity;
    }
}
