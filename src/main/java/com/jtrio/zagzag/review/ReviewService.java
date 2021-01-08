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
    public ReviewDTO createReview(ReviewCommand.CreateReview command, User user, Long orderId) {
        ProductOrder order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("order not found"));
        Product product = productRepository.findById(order.getProduct().getId()).orElseThrow(() -> new ProductNotFoundException("product not found"));
        if (!order.getUser().equals(user)) {
            throw new ReviewAuthorityException("리뷰 쓸 권한이 없음");
        }

        Review review = reviewRepository.save(command.toReview(user, order));
        product.setTotalProductScore(reviewRepository.avgProductScore(product.getId()));
        product.setTotalDeliveryScore(reviewRepository.avgDeliveryScore(product.getId()));
        return ReviewDTO.toDTO(review);
    }

    //리뷰조회
    public List<ReviewDTO> readReview(User user, Long productId, Pageable pageable) {
        productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("product not found"));
        List<Review> reviews = reviewRepository.findByProductId(productId, pageable);
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        for(Review r : reviews) {
            ReviewDTO dto = ReviewDTO.toDTO(r);
            reviewDTOS.add(dto);
        }

        //로그인유무에 따른 변화 user
        if (user != null) {
            for (int i=0 ; i<reviews.size() ; i++) {
                if (reviews.get(i).getLikers().contains(user)) {
                    reviewDTOS.get(i).setMyLike(true);
                }
            }
        }
        return reviewDTOS;
    }
}
