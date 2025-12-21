package com.starter.factory;

import java.util.UUID;

public class TraceIdGenerator {

    public String generate() {
        return UUID.randomUUID().toString();
    }
}
