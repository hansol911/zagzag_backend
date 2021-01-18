package com.jtrio.zagzag.likers;

import com.jtrio.zagzag.model.Likers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikersRepository extends JpaRepository<Likers, Long> {
    @Query("select count(l.id) from Likers l where l.review.id = :id")
    int countLikers(Long id);

    List<Likers> findByUserId(Long userId);

    List<Likers> findByReviewId(Long reviewId);

    boolean existsByUserIdAndAndReviewId(Long userId, Long reviewId);
}
