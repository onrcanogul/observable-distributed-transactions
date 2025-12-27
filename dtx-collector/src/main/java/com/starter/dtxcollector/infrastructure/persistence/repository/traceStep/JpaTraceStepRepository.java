package com.starter.dtxcollector.infrastructure.persistence.repository.traceStep;

import com.starter.dtxcollector.infrastructure.persistence.entity.TraceStepEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaTraceStepRepository extends JpaRepository<TraceStepEntity, Long> {
    List<TraceStepEntity> findByTraceIdOrderByStartedAtAsc(String traceId);
}
