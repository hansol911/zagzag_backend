package com.jtrio.zagzag.review;

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
    private Long userId;
    private Long productId;
    private Long orderId;
    //private List<User> likers;
}
