package com.jtrio.zagzag.qna;

import com.jtrio.zagzag.model.QnA;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnaRepository extends JpaRepository<QnA, Long> {
    List<QnA> findByProductId(Long productId, Pageable pageable);
}
