package io.npee.springwebadvanced.app.config.proxy;

import io.npee.springwebadvanced.app.v7.OrderServiceV7;
import io.npee.springwebadvanced.trace.TraceStatus;
import io.npee.springwebadvanced.trace.prod.LogTrace;

public class OrderServiceConcreteProxy extends OrderServiceV7 {

    private final OrderServiceV7 target;
    private final LogTrace trace;

    public OrderServiceConcreteProxy(OrderServiceV7 target, LogTrace trace) {
        super(null);
        this.target = target;
        this.trace = trace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");
            target.orderItem(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
