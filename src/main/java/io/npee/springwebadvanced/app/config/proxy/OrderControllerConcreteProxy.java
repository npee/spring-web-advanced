package io.npee.springwebadvanced.app.config.proxy;

import io.npee.springwebadvanced.app.v7.OrderControllerV7;
import io.npee.springwebadvanced.trace.TraceStatus;
import io.npee.springwebadvanced.trace.prod.LogTrace;

public class OrderControllerConcreteProxy extends OrderControllerV7 {

    private final OrderControllerV7 target;
    private final LogTrace trace;

    public OrderControllerConcreteProxy(OrderControllerV7 target, LogTrace trace) {
        super(null);
        this.target = target;
        this.trace = trace;
    }

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            String result = target.request(itemId);
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
        return target.noLog();
    }
}
