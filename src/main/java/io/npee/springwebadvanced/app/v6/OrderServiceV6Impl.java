package io.npee.springwebadvanced.app.v6;

public class OrderServiceV6Impl implements OrderServiceV6 {

    private final OrderRepositoryV6 orderRepository;

    public OrderServiceV6Impl(OrderRepositoryV6 orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
