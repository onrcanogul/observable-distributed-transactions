package com.starter.dtxcollector.domain.repository;

import com.starter.dtxcollector.domain.model.TraceStep;

import java.util.List;

public interface TraceStepRepository {
    List<TraceStep> findByTraceId(String traceId);

    void save(TraceStep traceStep);
}
