package com.jtrio.zagzag.order;

import com.jtrio.zagzag.model.ProductOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderDTO.CreateOrder createOrder(@RequestBody @Valid OrderCommand command, @RequestParam Long userId) {
        return orderService.createOrder(command, userId);
    }

    @GetMapping
    public List<OrderDTO.ReadOrder> findByOrder(@RequestParam Long userId, @RequestParam Long productId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime created, Pageable pageable) {
        return orderService.findByOrder(userId, productId, created, pageable);
    }
}
