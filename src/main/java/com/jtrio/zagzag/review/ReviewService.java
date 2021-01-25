package com.jtrio.zagzag.review;

import com.jtrio.zagzag.exception.*;
import com.jtrio.zagzag.likers.LikersRepository;
import com.jtrio.zagzag.model.*;
import com.jtrio.zagzag.order.OrderRepository;
import com.jtrio.zagzag.product.ProductRepository;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final LikersRepository likersRepository;

    //리뷰쓰기
    public ReviewDTO createReview(ReviewCommand command, Long userId, Long orderId) {
        User user = userRepository.findById(userId).orElseThrow();
        ProductOrder order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("order not found"));
        Product product = productRepository.findById(order.getProduct().getId()).orElseThrow(() -> new ProductNotFoundException("product not found"));
        if (!order.getUser().equals(user)) {
            throw new ReviewAuthorityException("cannot write review");
        }
        if (reviewRepository.existsByUserId(userId)) {
            throw new UserAuthorityException("already wrote a review");
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
        List<ReviewDTO> reviewDTOS = reviews.stream().map(ReviewDTO::toDTO).collect(Collectors.toList());
        if (userId != null) {
            List<Likers> likers = likersRepository.findByUserId(userId);
            List<Long> reviewId = likers.stream().map(l -> l.getReview().getId()).collect(Collectors.toList());
            for (int i = 0; i < reviews.size(); i++) {
                reviewDTOS.get(i).setMyLike(reviewId.contains(reviews.get(i).getId()));
            }
        }
        return reviewDTOS;
    }

    //리뷰수정
    public ReviewDTO updateReview(ReviewCommand command, Long userId, Long orderId) {
        User user = userRepository.findById(userId).orElseThrow();
        ProductOrder order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("order not found"));
        Review review = reviewRepository.findByOrderId(orderId);
        if (!review.getUser().equals(user)) {
            throw new UserAuthorityException("cannot update review");
        }
        reviewRepository.save(command.toReview(user, order));
        return ReviewDTO.toDTO(review);
    }

    //리뷰삭제
    public ReviewDTO deleteReview(Long userId, Long id) {
        User user = userRepository.findById(userId).orElseThrow();
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("review not found"));
        if (!review.getUser().equals(user)) {
            throw new UserAuthorityException("cannot delete review");
        }
        reviewRepository.delete(review);
        return ReviewDTO.toDTO(review);
    }
}