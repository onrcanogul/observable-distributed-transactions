package com.starter.context;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ThreadLocalTraceMetadataContext implements TraceMetadataContext {

    private static final ThreadLocal<Map<String, String>> CONTEXT = ThreadLocal.withInitial(Collections::emptyMap);

    @Override
    public Map<String, String> get() {
        return CONTEXT.get();
    }

    public static void set(Map<String, String> metadata) {
        if (metadata == null || metadata.isEmpty()) {
            CONTEXT.set(Collections.emptyMap());
        } else {
            CONTEXT.set(new HashMap<>(metadata)); // defensive copy
        }
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
