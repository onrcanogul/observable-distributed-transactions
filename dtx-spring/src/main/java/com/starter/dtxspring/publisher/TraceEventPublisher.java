package com.starter.dtxspring.publisher;

import com.starter.dtxspring.event.TraceStepEvent;

public interface TraceEventPublisher {
    void publish(TraceStepEvent event);
}
