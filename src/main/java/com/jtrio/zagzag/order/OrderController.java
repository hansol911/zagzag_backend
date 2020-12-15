package com.jtrio.zagzag.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderDTO createOrder(@RequestBody @Valid OrderCommand command, @RequestParam Long userId) {
        return orderService.createOrder(command, userId);
    }
}
