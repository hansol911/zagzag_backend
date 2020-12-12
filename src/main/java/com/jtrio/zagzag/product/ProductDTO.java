package com.jtrio.zagzag.product;

import com.jtrio.zagzag.model.Category;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Integer price;
    private String image;
    private Category category;
    private LocalDateTime created;
}
