package com.starter.dtxspring.filter;

import com.starter.context.TraceContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public String test() {
        var context = TraceContextHolder.get();
        return "traceId=" + context.getTraceId() +
                " spanId=" + context.getSpanId() +
                " parent=" + context.getParentSpanId();
    }
}
