package io.npee.springwebadvanced.app.v8;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping
@RestController
public class OrderControllerV8 {

    private final OrderServiceV8 orderService;

    public OrderControllerV8(OrderServiceV8 orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/v8/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/v8/no-log")
    public String noLog() {
        return "ok";
    }
}
