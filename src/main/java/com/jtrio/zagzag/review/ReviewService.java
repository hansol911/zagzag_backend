package com.jtrio.zagzag.review;

import com.jtrio.zagzag.exception.OrderNotFoundException;
import com.jtrio.zagzag.exception.ProductNotFoundException;
import com.jtrio.zagzag.exception.UserNotFoundException;
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
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    //리뷰쓰기
    public ReviewDTO createReview(ReviewCommand.CreateReview command, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("회원이 아님"));
        ProductOrder order = orderRepository.findById(command.getOrderId()).orElseThrow(() -> new OrderNotFoundException("주문한 회원이 아님"));
        Review review = reviewRepository.save(command.toReview(user, order));
        return review.toDTO();
    }

    /*//좋아요한 사람 보기??
    public List<Review> findByLiker(Long id) {
        List<Review> review = reviewRepository.findByUser(id);
        return review;
    }*/
}
