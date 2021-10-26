package io.npee.springwebadvanced.trace.custom;

import io.npee.springwebadvanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomTraceV1Test {

    @Test
    void begin_end() {
        CustomTraceV1 trace = new CustomTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    @Test
    void begin_exception() {
        CustomTraceV1 trace = new CustomTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
    }

}