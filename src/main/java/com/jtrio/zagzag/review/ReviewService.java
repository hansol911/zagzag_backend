package com.jtrio.zagzag.review;

import com.jtrio.zagzag.exception.*;
import com.jtrio.zagzag.model.*;
import com.jtrio.zagzag.order.OrderRepository;
import com.jtrio.zagzag.product.ProductRepository;
import com.jtrio.zagzag.qna.QnaDTO;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found"));
        ProductOrder order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("order not found"));
        Product product = productRepository.findById(order.getProduct().getId()).orElseThrow(() -> new ProductNotFoundException("product not found"));
        if (!order.getUser().getId().equals(userId)) {
            throw new ReviewAuthorityException("리뷰 쓸 권한이 없음");
        }

        Review review = reviewRepository.save(command.toReview(user, order));
        product.setTotalProductScore(reviewRepository.avgProductScore(product.getId()));
        product.setTotalDeliveryScore(reviewRepository.avgDeliveryScore(product.getId()));
        return ReviewDTO.toDTO(review);
    }

    //리뷰조회
    public List<ReviewDTO> readReview(Long userId, Long productId, Pageable pageable) {
        productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("product not found"));
        List<Review> reviews = reviewRepository.findByProductId(productId, pageable);
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        for(Review r : reviews) {
            ReviewDTO dto = ReviewDTO.toDTO(r);
            reviewDTOS.add(dto);
        }
        //내가 쓴 리뷰면 true 아니면 false
        if (userId != null) {
            for (int i=0 ; i<reviews.size() ; i++) {
                for (int j=0; j<reviews.get(i).getLikers().size(); j++){
                    if (reviews.get(i).getLikers().get(j).getId().equals(userId)) {
                        reviewDTOS.get(i).setMyLike(true);
                    }
                }
            }

        }
        return reviewDTOS;
    }
}
