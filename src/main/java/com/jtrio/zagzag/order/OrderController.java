package com.jtrio.zagzag.order;

import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.security.SecurityUser;
import com.jtrio.zagzag.user.UserCommand;
import com.jtrio.zagzag.user.UserDTO;
import com.jtrio.zagzag.user.UserService;
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
    private final UserService userService;

    @PostMapping
    public OrderDTO createOrder(@RequestBody @Valid OrderCommand command, @AuthenticationPrincipal SecurityUser securityUser) {
        Long userId = userService.findByEmail(securityUser.getUsername()).getId();
        return orderService.createOrder(command, userId);
    }

    @GetMapping
    public List<OrderDTO> readOrder(@AuthenticationPrincipal SecurityUser securityUser, @RequestParam Long productId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime created, Pageable pageable) {
        Long userId = userService.findByEmail(securityUser.getUsername()).getId();
        return orderService.readOrder(userId, productId, created, pageable);
    }

    @PutMapping(value = "/{id}")
    public OrderDTO deleteOrder(@AuthenticationPrincipal SecurityUser securityUser, @PathVariable Long id) {
        Long userId = userService.findByEmail(securityUser.getUsername()).getId();
        return orderService.deleteOrder(userId, id);
    }
}
