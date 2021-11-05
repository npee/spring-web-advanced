package io.npee.springwebadvanced.app.v8;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceV8 {
    private final OrderRepositoryV8 orderRepository;

    public OrderServiceV8(OrderRepositoryV8 orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
