package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.security.SecurityUser;
import com.jtrio.zagzag.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;

    @PostMapping
    public ReviewDTO createReview(@RequestBody @Valid ReviewCommand.CreateReview command, @AuthenticationPrincipal SecurityUser securityUser, @RequestParam Long orderId){
        Long userId = userService.findByEmail(securityUser.getUsername()).getId();
        return reviewService.createReview(command, userId, orderId);
    }

    @GetMapping
    public List<ReviewDTO> readReview(@AuthenticationPrincipal SecurityUser securityUser, @RequestParam Long productId, Pageable pageable){
        Long userId = userService.findByEmail(securityUser.getUsername()).getId();
        return reviewService.readReview(userId, productId, pageable);
    }
}
