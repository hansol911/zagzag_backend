package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.User;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReviewDTO {
    private String content;
    private String image;
    private byte productScore;
    private byte deliveryScore;
    private LocalDateTime created;
    private User user;
    private Product product;
    //private List<User> likers;
}
