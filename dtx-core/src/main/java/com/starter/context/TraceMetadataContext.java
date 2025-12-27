package com.starter.context;

import java.util.Map;

public interface TraceMetadataContext {

    /**
     * Returns current trace metadata bound to the execution context.
     * Never returns null.
     */
    Map<String, String> get();

}
