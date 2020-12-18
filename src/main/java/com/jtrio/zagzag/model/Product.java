package com.jtrio.zagzag.model;

import com.jtrio.zagzag.product.ProductDTO;
import com.jtrio.zagzag.user.UserDTO;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(value = {AuditingEntityListener.class}) //@CreatedDate, @LastModifiedDate 사용하기 위해
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private String image;
    private byte totalProductScore;
    private byte totalDeliveryScore;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime updated;

    public ProductDTO toDTO(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(id);
        productDTO.setName(name);
        productDTO.setPrice(price);
        productDTO.setImage(image);
        productDTO.setQuantity(quantity);
        productDTO.setCreated(created);
        productDTO.setCategoryId(category.getId());

        return productDTO;
    }
}
