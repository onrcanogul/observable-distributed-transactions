package com.starter.dtxspring.client;

import com.starter.context.TraceContext;
import com.starter.context.TraceContextHolder;
import com.starter.propagation.TraceHeaders;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

public class TraceWebClientFilter {

    public static ExchangeFilterFunction tracePropagation() {
        return ExchangeFilterFunction.ofRequestProcessor(request -> {

            TraceContext ctx = TraceContextHolder.get();

            if (ctx == null) {
                return Mono.just(request);
            }

            ClientRequest mutated = ClientRequest.from(request)
                    .header(TraceHeaders.TRACE_ID, ctx.getTraceId())
                    .header(TraceHeaders.SPAN_ID, ctx.getSpanId())
                    .headers(headers -> {
                        if (ctx.getSagaId() != null) {
                            headers.add(TraceHeaders.SAGA_ID, ctx.getSagaId());
                        }
                    })
                    .build();

            return Mono.just(mutated);
        });
    }
}
