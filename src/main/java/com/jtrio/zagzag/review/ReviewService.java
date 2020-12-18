package com.jtrio.zagzag.review;

import com.jtrio.zagzag.exception.*;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.order.OrderRepository;
import com.jtrio.zagzag.product.ProductRepository;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    //리뷰쓰기
    public ReviewDTO createReview(ReviewCommand.CreateReview command, Long userId, Long orderId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("회원이 아님"));
        ProductOrder order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("주문 x"));
        Product product = productRepository.findById(order.getProduct().getId()).orElseThrow(() -> new ProductNotFoundException("상품이 없음"));
        if (!order.getUser().getId().equals(userId)) {
            throw new ReviewAuthorityException("리뷰 쓸 권한이 없음");
        }

        Review review = reviewRepository.save(command.toReview(user, order));
        product.setTotalProductScore(reviewRepository.avgProductScore(product.getId()));
        product.setTotalDeliveryScore(reviewRepository.avgDeliveryScore(product.getId()));
        return review.toDTO();
    }

    /*//좋아요한 사람 보기??
    public List<Review> findByLiker(Long id) {
        List<Review> review = reviewRepository.findByUser(id);
        return review;
    }*/
}
