package io.npee.springwebadvanced.app.v7;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequestMapping
@ResponseBody
public class OrderControllerV7 {

    private final OrderServiceV7 orderService;

    public OrderControllerV7(OrderServiceV7 orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/v7/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/v7/no-log")
    public String noLog() {
        return "ok";
    }
}
