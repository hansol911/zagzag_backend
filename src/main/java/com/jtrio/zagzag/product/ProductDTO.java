package com.jtrio.zagzag.product;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Integer price;
    private String image;
    private int quantity;
    private Long categoryId;
    private LocalDateTime created;
}
