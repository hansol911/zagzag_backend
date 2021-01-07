package com.jtrio.zagzag.product;

import com.jtrio.zagzag.model.Product;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDTO {
    private String name;
    private Integer price;
    private String image;
    private int quantity;
    private Long categoryId;
    private LocalDateTime created;

    public static ProductDTO toDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setImage(product.getImage());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setCreated(product.getCreated());
        productDTO.setCategoryId(product.getCategory().getId());

        return productDTO;
    }

}
