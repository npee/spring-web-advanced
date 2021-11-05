package io.npee.springwebadvanced.app.v5;

import io.npee.springwebadvanced.trace.callback.TraceCallback;
import io.npee.springwebadvanced.trace.callback.TraceTemplate;
import io.npee.springwebadvanced.trace.prod.LogTrace;
import io.npee.springwebadvanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate traceTemplate;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.traceTemplate = new TraceTemplate(trace);
    }

    public void orderItem(String itemId) {

        traceTemplate.execute("OrderService.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });

    }
}
