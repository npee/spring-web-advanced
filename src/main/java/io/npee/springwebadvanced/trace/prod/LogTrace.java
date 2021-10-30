package io.npee.springwebadvanced.trace.prod;

import io.npee.springwebadvanced.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
