package com.starter.dtxcollector.infrastructure.persistence.repository;

import com.starter.dtxcollector.domain.model.TraceStep;
import com.starter.dtxcollector.domain.repository.TraceStepRepository;
import com.starter.dtxcollector.infrastructure.persistence.mapper.TraceStepMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TraceStepRepositoryImpl implements TraceStepRepository {
    private final JpaTraceStepRepository jpaRepository;

    public TraceStepRepositoryImpl(JpaTraceStepRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(TraceStep traceStep) {
        jpaRepository.save(
                TraceStepMapper.toEntity(traceStep)
        );
    }

    @Override
    public List<TraceStep> findByTraceId(String traceId) {
        return jpaRepository
                .findByTraceIdOrderByStartedAtAsc(traceId)
                .stream()
                .map(TraceStepMapper::toDomain)
                .collect(Collectors.toList());
    }
}
