package io.npee.springwebadvanced.app.v7;

import io.npee.springwebadvanced.app.v6.OrderRepositoryV6;

public class OrderServiceV7 {
    private final OrderRepositoryV7 orderRepository;

    public OrderServiceV7(OrderRepositoryV7 orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
