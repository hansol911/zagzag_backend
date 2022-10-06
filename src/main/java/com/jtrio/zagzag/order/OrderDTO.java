package com.jtrio.zagzag.order;

import com.jtrio.zagzag.model.ProductOrder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private String productName;
    private Integer orderPrice;
    private String image;
    private LocalDateTime created;

    public static OrderDTO toDTO(ProductOrder order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setProductName(order.getProduct().getName());
        orderDTO.setOrderPrice(order.getOrderPrice());
        orderDTO.setImage(order.getProduct().getImage());
        orderDTO.setCreated(order.getCreated());
        return orderDTO;
    }
}
