package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ReviewCommand {
    @NotBlank
    private String content;
    private String image;
    @Size(min = 1, max = 10)
    private byte productScore;
    @Size(min = 1, max = 10)
    private byte deliveryScore;

    public Review toReview(User user, ProductOrder order) {
        Review review = new Review();
        review.setContent(content);
        review.setImage(image);
        review.setProductScore(productScore);
        review.setDeliveryScore(deliveryScore);
        review.setUser(user);
        review.setProduct(order.getProduct());
        review.setOrder(order);
        return review;
    }
}