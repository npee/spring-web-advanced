package io.npee.springwebadvanced.app.v6;

public class OrderControllerV6Impl implements OrderControllerV6 {

    private final OrderServiceV6 orderService;

    public OrderControllerV6Impl(OrderServiceV6 orderService) {
        this.orderService = orderService;
    }

    @Override
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @Override
    public String noLog() {
        return "ok";
    }
}
