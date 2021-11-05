package io.npee.springwebadvanced.app.v6;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping
@ResponseBody
public interface OrderControllerV6 {

    @GetMapping("/v6/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v6/no-log")
    String noLog();
}
