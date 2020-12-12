package com.jtrio.zagzag.order;

import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Integer orderPrice;
    private Product product;
    private LocalDateTime created;
}
