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
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    //주문하기
    public OrderDTO createOrder(OrderCommand command, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("회원이 아님"));
        Product product = productRepository.findById(command.getProductId()).orElseThrow(() -> new ProductNotFoundException("상품이 없음"));
        if (product.getQuantity() == 0) {
            throw new ProductLackException("주문 가능한 수량이 없음");
        }
        product.setQuantity(product.getQuantity()-1);
        ProductOrder order = orderRepository.save(command.toOrder(user, product));
        return order.toDTO();
    }
}
