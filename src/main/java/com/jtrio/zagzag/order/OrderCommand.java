package com.jtrio.zagzag.order;

import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

import static com.jtrio.zagzag.enums.OrderStatus.NORMAL;

@Data
public class OrderCommand {
    @NotBlank
    private Long productId;

    public ProductOrder toOrder(User user, Product product) {
        ProductOrder order = new ProductOrder();
        order.setOrderPrice(product.getPrice());
        order.setUser(user);
        order.setProduct(product);
        order.setOrderStatus(NORMAL);
        return order;
    }
}
