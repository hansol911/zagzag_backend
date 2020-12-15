package com.jtrio.zagzag.model;

import com.jtrio.zagzag.order.OrderDTO;
import com.jtrio.zagzag.product.ProductDTO;
import jdk.jfr.DataAmount;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(value = {AuditingEntityListener.class})
@Data
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer orderPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime updated;

    public OrderDTO toDTO(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderPrice(orderPrice);
        orderDTO.setProductId(product.getId());
        orderDTO.setCreated(created);

        return orderDTO;
    }
}
