package com.starter.dtxcollector.presentation.api;

import com.starter.dtxcollector.application.service.TraceIngestService;
import com.starter.dtxcollector.domain.model.TraceStep;
import com.starter.dtxcollector.presentation.dto.TraceIngestRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/trace")
@RestController
public class TraceStepController {

    private final TraceIngestService ingestService;

    public TraceStepController(TraceIngestService ingestService) {
        this.ingestService = ingestService;
    }

    @PostMapping("/ingest")
    public void ingest(TraceIngestRequest model) {
        TraceStep step = new TraceStep(
                model.traceId(),
                model.spanId(),
                model.parentSpanId(),
                model.serviceName(),
                model.operationName(),
                model.startedAt(),
                model.endedAt(),
                model.status(),
                model.errorMessage()
        );

        ingestService.save(step);
    }
}
