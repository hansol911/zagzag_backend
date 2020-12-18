package com.jtrio.zagzag.model;

import com.jtrio.zagzag.order.OrderDTO;
import com.jtrio.zagzag.review.ReviewDTO;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(value = {AuditingEntityListener.class})
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String image;
    private byte productScore;
    private byte deliveryScore;

    @ManyToMany
    private List<User> likers;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @OneToOne
    @JoinColumn(name = "order_id")
    private ProductOrder order;

    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime updated;

    public ReviewDTO toDTO(){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setContent(content);
        reviewDTO.setImage(image);
        reviewDTO.setProductScore(productScore);
        reviewDTO.setDeliveryScore(deliveryScore);
        reviewDTO.setCreated(created);

        return reviewDTO;
    }
}
