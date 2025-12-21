package com.starter.dtxspring.config;

import com.starter.context.TraceContextFactory;
import com.starter.dtxspring.client.TraceRestTemplateInterceptor;
import com.starter.dtxspring.filter.TraceInboundFilter;
import com.starter.dtxspring.support.TraceContextExtractor;
import com.starter.factory.SpanIdGenerator;
import com.starter.factory.TraceIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DtxSpringConfiguration {

    @Bean
    public TraceIdGenerator traceIdGenerator() {
        return new TraceIdGenerator();
    }

    @Bean
    public SpanIdGenerator spanIdGenerator() {
        return new SpanIdGenerator();
    }

    @Bean
    public TraceContextFactory traceContextFactory(
            TraceIdGenerator traceIdGenerator,
            SpanIdGenerator spanIdGenerator
    ) {
        return new TraceContextFactory(traceIdGenerator, spanIdGenerator);
    }

    @Bean
    public TraceContextExtractor traceContextExtractor(TraceContextFactory factory) {
        return new TraceContextExtractor(factory);
    }

    @Bean
    public TraceInboundFilter traceInboundFilter(
            TraceContextExtractor extractor
    ) {
        return new TraceInboundFilter(extractor);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new TraceRestTemplateInterceptor());
        return restTemplate;
    }
}
