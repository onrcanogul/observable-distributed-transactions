package com.starter.context;

public final class TraceContextHolder {

    private static final ThreadLocal<TraceContext> HOLDER = new ThreadLocal<>();

    private TraceContextHolder() {

    }

    public static void set(TraceContext context) {
        HOLDER.set(context);
    }

    public static TraceContext get() {
        return HOLDER.get();
    }

    public static void clear() {
        HOLDER.remove();
    }
}
