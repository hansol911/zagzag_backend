package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.util.Nickname;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDTO {
    @Data
    public static class CreateReview {
        private String userEmail;
        private String content;
        private String image;
        private byte productScore;
        private byte deliveryScore;
        private LocalDateTime created;

        public static CreateReview toDTO(Review review) {
            CreateReview reviewDTO = new CreateReview();
            reviewDTO.setUserEmail(review.getUser().getEmail());
            reviewDTO.setContent(review.getContent());
            reviewDTO.setImage(review.getImage());
            reviewDTO.setProductScore(review.getProductScore());
            reviewDTO.setDeliveryScore(review.getDeliveryScore());
            return reviewDTO;
        }
    }

    @Data
    public static class ReadReview {
        private String nickname;
        private String content;
        private String image;
        private byte productScore;
        private byte deliveryScore;
        private LocalDateTime created;
        private int likers;
        private boolean myLike;

        public static ReadReview toDTO(Review review, int liker) {
            ReadReview reviewDTO = new ReadReview();
            reviewDTO.setNickname(Nickname.getNick(review.getUser().getEmail()));
            reviewDTO.setContent(review.getContent());
            reviewDTO.setImage(review.getImage());
            reviewDTO.setProductScore(review.getProductScore());
            reviewDTO.setDeliveryScore(review.getDeliveryScore());
            reviewDTO.setLikers(liker);
            reviewDTO.setCreated(review.getCreated());
            return reviewDTO;
        }
    }

    @Data
    public static class DeleteReview {
        private String content;

        public static DeleteReview toDTO() {
            DeleteReview reviewDTO = new DeleteReview();
            reviewDTO.setContent("삭제된 리뷰입니다.");
            return reviewDTO;
        }
    }
}
