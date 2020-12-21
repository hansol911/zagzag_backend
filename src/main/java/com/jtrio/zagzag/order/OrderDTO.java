package com.jtrio.zagzag.order;

import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Integer orderPrice;
    private Long productId;
    private LocalDateTime created;

    public static OrderDTO toDTO(ProductOrder order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderPrice(order.getOrderPrice());
        orderDTO.setProductId(order.getProduct().getId());
        orderDTO.setCreated(order.getCreated());

        return orderDTO;
    }
}
