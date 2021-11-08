package io.npee.springwebadvanced.app.config.proxy;

import io.npee.springwebadvanced.app.v6.OrderControllerV6;
import io.npee.springwebadvanced.trace.TraceStatus;
import io.npee.springwebadvanced.trace.prod.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV6 {

    private final OrderControllerV6 target;
    private final LogTrace trace;

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
