package com.starter.dtxspring.filter;

import com.starter.context.TraceContext;
import com.starter.context.TraceContextHolder;
import com.starter.dtxspring.support.TraceContextExtractor;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class TraceInboundFilter extends OncePerRequestFilter {

    private final TraceContextExtractor extractor;

    public TraceInboundFilter(TraceContextExtractor extractor) {
        this.extractor = extractor;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        TraceContext context = extractor.extract(request);

        try {
            TraceContextHolder.set(context);
            filterChain.doFilter(request, response);
        } finally {
            TraceContextHolder.clear();
        }
    }
}
