package io.npee.springwebadvanced.app.v1;

import io.npee.springwebadvanced.trace.TraceStatus;
import io.npee.springwebadvanced.trace.custom.CustomTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;
    private final CustomTraceV1 trace;

    public void orderItem(String itemId) {

        TraceStatus status = null;
        try {
            status = this.trace.begin("OrderService.request()");
            orderRepository.save(itemId);
            trace.end(status);

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

    }
}
