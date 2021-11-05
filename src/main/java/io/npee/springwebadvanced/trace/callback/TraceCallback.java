package io.npee.springwebadvanced.trace.callback;

public interface TraceCallback<T> {
    T call();
}
