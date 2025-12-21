package com.starter.factory;

import java.util.UUID;

public class SpanIdGenerator {

    public String generate() {
        return UUID.randomUUID().toString();
    }
}
