package com.jtrio.zagzag.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderDTO orderProduct(@RequestBody OrderCommand command, @RequestParam Long userId) {
        return orderService.orderProduct(command, userId);
    }
}
