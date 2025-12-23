package com.starter.dtxcollector.application.service;

import com.starter.dtxcollector.domain.model.TraceStep;
import com.starter.dtxcollector.domain.repository.TraceStepRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TraceIngestService {

    private final TraceStepRepository repository;

    public TraceIngestService(TraceStepRepository repository) {
        this.repository = repository;
    }



    @Transactional
    public void save(TraceStep model) {
        repository.save(model);
    }

}
