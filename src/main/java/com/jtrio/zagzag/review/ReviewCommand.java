package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.model.User;
import lombok.Data;

import java.util.List;

@Data
public class ReviewCommand {

    @Data
    public static class CreateReview{
        private String content;
        private String image;
        private byte productScore;
        private byte deliveryScore;
        private Long productId;

        public Review toReview(User user, Product product) {
            Review review = new Review();
            review.setContent(content);
            review.setImage(image);
            review.setProductScore(productScore);
            review.setDeliveryScore(deliveryScore);
            review.setUser(user);
            review.setProduct(product);

            return review;
        }
    }

    /*public static class ReviewLike {

    }*/

}
