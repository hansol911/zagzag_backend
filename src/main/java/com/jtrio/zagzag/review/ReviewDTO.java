package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.Review;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReviewDTO {
    private String content;
    private String image;
    private byte productScore;
    private byte deliveryScore;
    private LocalDateTime created;
    //private List<User> likers;

    public static ReviewDTO toDTO(Review review){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setContent(review.getContent());
        reviewDTO.setImage(review.getImage());
        reviewDTO.setProductScore(review.getProductScore());
        reviewDTO.setDeliveryScore(review.getDeliveryScore());
        reviewDTO.setCreated(review.getCreated());

        return reviewDTO;
    }
}
