package com.jtrio.zagzag.order;

import com.jtrio.zagzag.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public OrderDTO createOrder(@RequestBody @Valid OrderCommand command, @AuthenticationPrincipal SecurityUser securityUser) {
        return orderService.createOrder(command, securityUser.getUserId());
    }

    @GetMapping
    public List<OrderDTO> readOrder(@AuthenticationPrincipal SecurityUser securityUser, @RequestParam Long productId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime created, Pageable pageable) {
        return orderService.readOrder(securityUser.getUserId(), productId, created, pageable);
    }

    @PutMapping(value = "/{id}")
    public OrderDTO deleteOrder(@AuthenticationPrincipal SecurityUser securityUser, @PathVariable Long id) {
        return orderService.deleteOrder(securityUser.getUserId(), id);
    }
}
