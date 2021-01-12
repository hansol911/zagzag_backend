package com.jtrio.zagzag.order;

import com.jtrio.zagzag.model.ProductOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<ProductOrder, Long> {
    boolean existsByProductIdAndUserId(Long productId, Long userId);

    List<ProductOrder> findAllByUserIdAndProductIdAndCreatedAfter(Long userId, Long productId, LocalDateTime created, Pageable pageable);
}
