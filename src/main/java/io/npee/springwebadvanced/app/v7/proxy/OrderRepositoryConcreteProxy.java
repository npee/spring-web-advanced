package io.npee.springwebadvanced.app.v7.proxy;

import io.npee.springwebadvanced.app.v7.OrderRepositoryV7;
import io.npee.springwebadvanced.trace.TraceStatus;
import io.npee.springwebadvanced.trace.prod.LogTrace;

public class OrderRepositoryConcreteProxy extends OrderRepositoryV7 {

    private final OrderRepositoryV7 target;
    private final LogTrace trace;

    public OrderRepositoryConcreteProxy(OrderRepositoryV7 target, LogTrace trace) {
        this.target = target;
        this.trace = trace;
    }

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.save()");
            target.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

    }
}
