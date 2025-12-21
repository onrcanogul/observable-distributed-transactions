package com.starter.dtxspring.config;

import com.starter.context.TraceContextFactory;
import com.starter.dtxspring.aop.TraceAspect;
import com.starter.dtxspring.client.TraceRestTemplateInterceptor;
import com.starter.dtxspring.client.TraceWebClientFilter;
import com.starter.dtxspring.filter.TraceInboundFilter;
import com.starter.dtxspring.publisher.TraceEventPublisher;
import com.starter.dtxspring.publisher.impl.HttpTraceEventPublisher;
import com.starter.dtxspring.support.SpanLifecycleManager;
import com.starter.dtxspring.support.TraceContextExtractor;
import com.starter.factory.SpanIdGenerator;
import com.starter.factory.TraceIdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class DtxSpringConfiguration {

    // Core trace identity generators
    // Responsible for generating traceId and spanId values

    @Bean
    public TraceIdGenerator traceIdGenerator() {
        return new TraceIdGenerator();
    }

    @Bean
    public SpanIdGenerator spanIdGenerator() {
        return new SpanIdGenerator();
    }

    // TraceContext factory
    // Encapsulates rules for creating root and child TraceContext instances

    @Bean
    public TraceContextFactory traceContextFactory(
            TraceIdGenerator traceIdGenerator,
            SpanIdGenerator spanIdGenerator
    ) {
        return new TraceContextFactory(traceIdGenerator, spanIdGenerator);
    }

    // Inbound trace extraction support
    // Extracts trace information from incoming HTTP requests

    @Bean
    public TraceContextExtractor traceContextExtractor(
            TraceContextFactory factory
    ) {
        return new TraceContextExtractor(factory);
    }

    // Inbound HTTP filter
    // Initializes TraceContext at the beginning of a request
    // and clears it at the end of the request lifecycle

    @Bean
    public TraceInboundFilter traceInboundFilter(
            TraceContextExtractor extractor
    ) {
        return new TraceInboundFilter(extractor);
    }

    // Outbound HTTP client - RestTemplate
    // Automatically propagates trace headers for synchronous HTTP calls

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new TraceRestTemplateInterceptor());
        return restTemplate;
    }

    // Outbound HTTP client - WebClient
    // Automatically propagates trace headers for reactive HTTP calls

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .filter(TraceWebClientFilter.tracePropagation())
                .build();
    }

    @Bean
    public SpanLifecycleManager spanLifecycleManager(
            TraceContextFactory factory
    ) {
        return new SpanLifecycleManager(factory);
    }

    @Bean
    public TraceEventPublisher traceEventPublisher(RestTemplate restTemplate) {
        return new HttpTraceEventPublisher(
                restTemplate,
                "http://localhost:9090" // collector endpoint
        );
    }

    @Bean
    public TraceAspect traceAspect(
            SpanLifecycleManager spanManager,
            TraceEventPublisher publisher,
            @Value("${spring.application.name}") String serviceName
    ) {
        return new TraceAspect(spanManager, publisher, serviceName);
    }

}
