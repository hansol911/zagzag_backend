package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ReviewDTO createReview(@RequestBody @Valid ReviewCommand.CreateReview command, @RequestParam Long userId, @RequestParam Long orderId){
        return reviewService.createReview(command, userId, orderId);
    }

    /*@GetMapping(value = "/users/{id}")
    public ResponseEntity<List<Review>> findByLiker(@PathVariable("id") Long id){
        List<Review> review = reviewService.findByLiker(id);
        return new ResponseEntity<List<Review>>(review, HttpStatus.OK);
    }*/
}
