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
    @Data
    public static class CreateOrder {
        private Integer orderPrice;
        private Long productId;
        private LocalDateTime created;

        public static CreateOrder toDTO(ProductOrder order){
            CreateOrder orderDTO = new CreateOrder();
            orderDTO.setOrderPrice(order.getOrderPrice());
            orderDTO.setProductId(order.getProduct().getId());
            orderDTO.setCreated(order.getCreated());

            return orderDTO;
        }
    }

    @Data
    public static class ReadOrder {
        private String productName;
        private Integer orderPrice;
        private String image;
        private LocalDateTime created;

        public static ReadOrder toDTO(ProductOrder order){
            ReadOrder orderDTO = new ReadOrder();
            orderDTO.setProductName(order.getProduct().getName());
            orderDTO.setOrderPrice(order.getOrderPrice());
            orderDTO.setImage(order.getProduct().getImage());
            orderDTO.setCreated(order.getCreated());

            return orderDTO;
        }
    }

}
