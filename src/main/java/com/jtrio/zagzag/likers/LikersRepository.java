package com.jtrio.zagzag.likers;

import com.jtrio.zagzag.model.Likers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LikersRepository extends JpaRepository<Likers, Long> {
    @Query("select count(l.id) from Likers l where l.review.id = :id")
    int countLikers(Long id);

    Likers findByUserId(Long userId);
}
