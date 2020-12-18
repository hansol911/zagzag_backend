package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.model.User;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class ReviewCommand {

    @Data
    public static class CreateReview{
        @NotBlank
        private String content;
        private String image;
        @Min(2) @Max(10)
        private byte productScore;
        @Min(2) @Max(10)
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

    /*public static class ReviewLike {

    }*/

}
