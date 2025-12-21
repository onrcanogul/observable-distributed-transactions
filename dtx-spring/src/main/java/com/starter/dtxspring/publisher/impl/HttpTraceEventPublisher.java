package com.starter.dtxspring.publisher.impl;

import com.starter.dtxspring.event.TraceStepEvent;
import com.starter.dtxspring.publisher.TraceEventPublisher;
import org.springframework.web.client.RestTemplate;

public class HttpTraceEventPublisher implements TraceEventPublisher {

    private final RestTemplate restTemplate;
    private final String collectorUrl;

    public HttpTraceEventPublisher(
            RestTemplate restTemplate,
            String collectorUrl
    ) {
        this.restTemplate = restTemplate;
        this.collectorUrl = collectorUrl;
    }

    @Override
    public void publish(TraceStepEvent event) {
        try {
            restTemplate.postForEntity(
                    collectorUrl + "/ingest",
                    event,
                    Void.class
            );
        } catch (Exception e) {
            // Fail-safe: tracing must NEVER break business flow
        }
    }
}
