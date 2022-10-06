package com.jtrio.zagzag.product;

import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class ProductCommand {
    @NotBlank
    private String name;
    @Min(1)
    private Integer price;
    @NotBlank
    private String image;
    @NotBlank
    private int quantity;
    @NotBlank
    private Long categoryId;

    public Product toProduct(Category category) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setImage(image);
        product.setQuantity(quantity);
        product.setCategory(category);
        return product;
    }
}