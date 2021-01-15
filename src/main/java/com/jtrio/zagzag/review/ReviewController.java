package com.jtrio.zagzag.review;

import com.jtrio.zagzag.aop.NoLogging;
import com.jtrio.zagzag.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ReviewDTO createReview(@RequestBody @Valid ReviewCommand command, @AuthenticationPrincipal SecurityUser securityUser, @RequestParam Long orderId) {
        return reviewService.createReview(command, securityUser.getUserId(), orderId);
    }

    @NoLogging
    @GetMapping
    public List<ReviewDTO> readReview(@AuthenticationPrincipal SecurityUser securityUser, @RequestParam Long productId, Pageable pageable) {
        return reviewService.readReview(securityUser != null ? securityUser.getUserId() : null, productId, pageable);
    }

    @PutMapping(value = "/{id}")
    public ReviewDTO updateReview(@RequestBody ReviewCommand command, @AuthenticationPrincipal SecurityUser securityUser, @PathVariable Long id) {
        return reviewService.updateReview(command, securityUser.getUserId(), id);
    }

    @DeleteMapping(value = "/{id}")
    public ReviewDTO deleteReview(@AuthenticationPrincipal SecurityUser securityUser, @PathVariable Long id) {
        return reviewService.deleteReview(securityUser.getUserId(), id);
    }
}
