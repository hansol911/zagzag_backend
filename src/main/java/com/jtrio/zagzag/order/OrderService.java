package com.jtrio.zagzag.order;

import com.jtrio.zagzag.exception.UserAuthorityException;
import com.jtrio.zagzag.exception.OrderNotFoundException;
import com.jtrio.zagzag.exception.ProductLackException;
import com.jtrio.zagzag.exception.ProductNotFoundException;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.product.ProductRepository;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.jtrio.zagzag.enums.OrderStatus.CANCELED;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    //주문하기
    public OrderDTO createOrder(OrderCommand command, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Product product = productRepository.findById(command.getProductId()).orElseThrow(() -> new ProductNotFoundException("상품이 없음"));
        if (product.getQuantity() == 0) {
            throw new ProductLackException("lack of quantity");
        }
        product.setQuantity(product.getQuantity() - 1);
        ProductOrder order = orderRepository.save(command.toOrder(user, product));
        return OrderDTO.toDTO(order);
    }

    //주문조회
    public List<OrderDTO> readOrder(Long userId, Long productId, LocalDateTime created, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow();
        productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("product not found"));
        List<ProductOrder> orders = orderRepository.findAllByUserIdAndProductIdAndCreatedAfter(user.getId(), productId, created, pageable);
        return orders.stream().map(OrderDTO::toDTO).collect(Collectors.toList());
    }

    //주문취소
    public OrderDTO deleteOrder(Long userId, Long id) {
        User user = userRepository.findById(userId).orElseThrow();
        ProductOrder order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("order not found"));
        if (!order.getUser().equals(user)) {
            throw new UserAuthorityException("cannot cancel order");
        }
        order.setOrderStatus(CANCELED);
        orderRepository.save(order);
        return OrderDTO.toDTO(order);
    }
}
