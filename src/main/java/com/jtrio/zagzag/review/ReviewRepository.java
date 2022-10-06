package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByUserId(Long id);

    List<Review> findByProductId(Long productId, Pageable pageable);

    Review findByOrderId(Long orderId);

    @Query("select avg(r.productScore) from Review r where r.product.id = :id")
    byte avgProductScore(Long id);

    @Query("select avg(r.deliveryScore) from Review r where r.product.id = :id")
    byte avgDeliveryScore(Long id);
}
