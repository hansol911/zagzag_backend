package com.jtrio.zagzag.order;

import com.jtrio.zagzag.exception.ProductLackException;
import com.jtrio.zagzag.exception.ProductNotFoundException;
import com.jtrio.zagzag.exception.UserNotFoundException;
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
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    //주문하기
    public OrderDTO.CreateOrder createOrder(OrderCommand command, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("회원이 아님"));
        Product product = productRepository.findById(command.getProductId()).orElseThrow(() -> new ProductNotFoundException("상품이 없음"));
        if (product.getQuantity() == 0) {
            throw new ProductLackException("주문 가능한 수량이 없음");
        }
        product.setQuantity(product.getQuantity()-1);
        ProductOrder order = orderRepository.save(command.toOrder(user, product));
        return OrderDTO.CreateOrder.toDTO(order);
    }

    //주문조회
    public List<OrderDTO.ReadOrder> findByOrder(Long userId, Long productId, LocalDateTime created, Pageable pageable) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("회원이 아님"));
        productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("상품이 없음"));
        List<ProductOrder> orders = orderRepository.findAllByUserIdAndProductIdAndCreatedAfter(userId, productId, created, pageable);
        List<OrderDTO.ReadOrder> orderDTOS = new ArrayList<>();
        for(ProductOrder po : orders) {
            OrderDTO.ReadOrder dto = OrderDTO.ReadOrder.toDTO(po);
            orderDTOS.add(dto);
        }
        return orderDTOS;
    }
}
