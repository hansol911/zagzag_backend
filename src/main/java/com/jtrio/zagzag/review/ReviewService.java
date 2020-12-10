package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.Review;
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

    public List<Review> findByLiker(Long id) {
        List<Review> review = reviewRepository.findByUser(id);
        return review;
    }
}
