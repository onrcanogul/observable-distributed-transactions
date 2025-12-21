package com.starter.dtxspring.client;

import com.starter.context.TraceContext;
import com.starter.context.TraceContextHolder;
import com.starter.propagation.TraceHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class TraceRestTemplateInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution
    ) throws IOException {

        TraceContext context = TraceContextHolder.get();

        if (context != null) {
            request.getHeaders().add(TraceHeaders.TRACE_ID, context.getTraceId());
            request.getHeaders().add(TraceHeaders.SPAN_ID, context.getSpanId());

            if(context.getSagaId() != null) {
                request.getHeaders().add(TraceHeaders.SAGA_ID, context.getSagaId());
            }
        }

        return execution.execute(request, body);
    }
}
