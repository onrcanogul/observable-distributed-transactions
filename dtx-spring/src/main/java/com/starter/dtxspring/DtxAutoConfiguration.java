package com.starter.dtxspring;

import com.starter.context.ThreadLocalTraceMetadataContext;
import com.starter.context.TraceContextFactory;
import com.starter.context.TraceMetadataContext;
import com.starter.dtxspring.aop.TraceAspect;
import com.starter.dtxspring.client.TraceRestTemplateInterceptor;
import com.starter.dtxspring.client.TraceWebClientFilter;
import com.starter.dtxspring.filter.TraceInboundFilter;
import com.starter.dtxspring.properties.DtxProperties;
import com.starter.dtxspring.publisher.TraceEventPublisher;
import com.starter.dtxspring.publisher.impl.HttpTraceEventPublisher;
import com.starter.dtxspring.support.SpanLifecycleManager;
import com.starter.dtxspring.support.TraceContextExtractor;
import com.starter.factory.SpanIdGenerator;
import com.starter.factory.TraceIdGenerator;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@AutoConfiguration
@EnableConfigurationProperties(DtxProperties.class)
@ConditionalOnProperty(prefix = "dtx", name = "enabled", havingValue = "true", matchIfMissing = true)
public class DtxAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TraceIdGenerator traceIdGenerator() {
        return new TraceIdGenerator();
    }

    @Bean
    @ConditionalOnMissingBean
    public SpanIdGenerator spanIdGenerator() {
        return new SpanIdGenerator();
    }

    @Bean
    @ConditionalOnMissingBean
    public TraceContextFactory traceContextFactory(
            TraceIdGenerator traceIdGenerator,
            SpanIdGenerator spanIdGenerator
    ) {
        return new TraceContextFactory(traceIdGenerator, spanIdGenerator);
    }

    @Bean
    @ConditionalOnMissingBean
    public TraceContextExtractor traceContextExtractor(TraceContextFactory factory) {
        return new TraceContextExtractor(factory);
    }

    // Inbound filter: registration + order
    @Bean
    @ConditionalOnClass(name = "jakarta.servlet.Filter")
    public FilterRegistrationBean<TraceInboundFilter> traceInboundFilterRegistration(
            TraceContextExtractor extractor,
            DtxProperties props
    ) {
        TraceInboundFilter filter = new TraceInboundFilter(extractor);

        FilterRegistrationBean<TraceInboundFilter> reg = new FilterRegistrationBean<>();
        reg.setFilter(filter);
        reg.setOrder(props.getInboundFilterOrder());
        reg.addUrlPatterns("/*");
        return reg;
    }

    @Bean
    @ConditionalOnMissingBean
    public TraceMetadataContext traceMetadataContext() {
        return new ThreadLocalTraceMetadataContext();
    }

    @Bean
    @ConditionalOnMissingBean
    public SpanLifecycleManager spanLifecycleManager(TraceContextFactory factory) {
        return new SpanLifecycleManager(factory);
    }

    // --- Outbound: RestTemplate customization ---
    @Bean
    @ConditionalOnClass(RestTemplate.class)
    @ConditionalOnBean(RestTemplateBuilder.class)
    @ConditionalOnProperty(prefix = "dtx.http", name = "rest-template-enabled", havingValue = "true", matchIfMissing = true)
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .additionalInterceptors(new TraceRestTemplateInterceptor())
                .build();
    }

    // --- Outbound: WebClient customization ---
    @Bean
    @ConditionalOnClass(WebClient.class)
    @ConditionalOnProperty(prefix = "dtx.http", name = "web-client-enabled", havingValue = "true", matchIfMissing = true)
    @ConditionalOnMissingBean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .filter(TraceWebClientFilter.tracePropagation());
    }

    // Publisher (HTTP)
    @Bean
    @ConditionalOnProperty(prefix = "dtx.collector", name = "enabled", havingValue = "true", matchIfMissing = true)
    @ConditionalOnBean(RestTemplate.class)
    @ConditionalOnMissingBean
    public TraceEventPublisher traceEventPublisher(RestTemplate restTemplate, DtxProperties props) {
        return new HttpTraceEventPublisher(restTemplate, props.getCollector().getEndpoint());
    }

    // Aspect
    @Bean
    @ConditionalOnMissingBean
    public TraceAspect traceAspect(
            SpanLifecycleManager spanManager,
            TraceEventPublisher publisher,
            DtxProperties props
    ) {
        return new TraceAspect(spanManager, publisher, props.getServiceName());
    }
}
