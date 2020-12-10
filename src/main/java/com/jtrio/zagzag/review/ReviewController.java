package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<List<Review>> findByLiker(@PathVariable("id") Long id){
        List<Review> review = reviewService.findByLiker(id);
        return new ResponseEntity<List<Review>>(review, HttpStatus.OK);
    }
}
