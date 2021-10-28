package io.npee.springwebadvanced.trace.custom;

import io.npee.springwebadvanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class CustomTraceV2Test {

    @Test
    void begin_end() {
        CustomTraceV2 trace = new CustomTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.end(status2);
        trace.end(status1);
    }



    @Test
    void begin_exception() {
        CustomTraceV2 trace = new CustomTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }

}