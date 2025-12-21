package com.starter.dtxcollector.infrastructure.persistence.mapper;

import com.starter.dtxcollector.domain.model.TraceStep;
import com.starter.dtxcollector.infrastructure.persistence.entity.TraceStepEntity;

public final class TraceStepMapper {
    private TraceStepMapper() {}

    public static TraceStepEntity toEntity(TraceStep domain) {
        return TraceStepEntity.of(
                domain.getTraceId(),
                domain.getSpanId(),
                domain.getParentSpanId(),
                domain.getServiceName(),
                domain.getOperationName(),
                domain.getStartedAt(),
                domain.getEndedAt(),
                domain.getStatus(),
                domain.getErrorMessage()
        );
    }

    public static TraceStep toDomain(TraceStepEntity entity) {
        return new TraceStep(
                entity.getTraceId(),
                entity.getSpanId(),
                entity.getParentSpanId(),
                entity.getServiceName(),
                entity.getOperationName(),
                entity.getStartedAt(),
                entity.getEndedAt(),
                entity.getStatus(),
                entity.getErrorMessage()
        );
    }


}
