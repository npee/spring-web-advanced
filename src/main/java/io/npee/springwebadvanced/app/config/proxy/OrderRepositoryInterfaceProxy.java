package io.npee.springwebadvanced.app.config.proxy;

import io.npee.springwebadvanced.app.v6.OrderRepositoryV6;
import io.npee.springwebadvanced.trace.TraceStatus;
import io.npee.springwebadvanced.trace.prod.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV6 {

    private final OrderRepositoryV6 target;
    private final LogTrace trace;

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
