package com.starter.dtxcollector.infrastructure.persistence.repository.traceStep;

import com.starter.dtxcollector.domain.model.TraceStep;
import com.starter.dtxcollector.domain.repository.TraceStepRepository;
import com.starter.dtxcollector.infrastructure.configuration.mapper.Mapper;
import com.starter.dtxcollector.infrastructure.persistence.entity.TraceStepEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TraceStepRepositoryImpl implements TraceStepRepository {
    private final JpaTraceStepRepository jpaRepository;
    private final Mapper<TraceStepEntity, TraceStep> mapper;

    public TraceStepRepositoryImpl(JpaTraceStepRepository jpaRepository, Mapper<TraceStepEntity, TraceStep> mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(TraceStep traceStep) {
        jpaRepository.save(mapper.toEntity(traceStep));
    }

    @Override
    public List<TraceStep> findByTraceId(String traceId) {
        return jpaRepository
                .findByTraceIdOrderByStartedAtAsc(traceId)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
