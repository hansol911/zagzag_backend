package com.jtrio.zagzag.order;

import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.User;
import lombok.Data;

import javax.persistence.criteria.Order;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
@Data
public class OrderCommand {
    @NotBlank
    private Long productId;

    public ProductOrder toOrder(User user, Product product) {
        ProductOrder order = new ProductOrder();
        order.setOrderPrice(product.getPrice());
        order.setUser(user);
        order.setProduct(product);

        return order;
    }
}
