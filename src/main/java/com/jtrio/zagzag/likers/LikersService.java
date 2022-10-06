package com.jtrio.zagzag.likers;

import com.jtrio.zagzag.exception.ReviewNotFoundException;
import com.jtrio.zagzag.exception.UserAuthorityException;
import com.jtrio.zagzag.model.Likers;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.review.ReviewRepository;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikersService {
    private final LikersRepository likersRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    //좋아요
    public Likers createLike(LikersCommand command, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Review review = reviewRepository.findById(command.getReviewId()).orElseThrow(() -> new ReviewNotFoundException("review not found"));
        if (likersRepository.existsByUserIdAndAndReviewId(user.getId(), review.getId())) {
            throw new UserAuthorityException("already liked");
        }
        return likersRepository.save(command.toLikers(user, review));
    }
}
