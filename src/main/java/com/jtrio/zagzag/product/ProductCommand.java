package com.jtrio.zagzag.product;

import com.jtrio.zagzag.category.CategoryRepository;
import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
public class ProductCommand {
    @NotBlank
    private String name;
    @NotBlank
    private Integer price;
    @NotBlank
    private String image;
    @NotBlank
    private Long categoryId;

    public Product toProduct(Category category) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setImage(image);
        product.setCategory(category);

        return product;
    }

    /*
    @Data
    public static class CreateProduct {
        private String name;
        private Integer price;
        private String image;
        private Category category;

        public Product toProduct() {
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setImage(image);
            product.setCategory(category);

            return product;
        }
    }

    @Data
    public static class ReadProduct {
        private String name;
        private Integer price;
        private String image;
        private Category category;

        public Product toProduct(Product product) {
            product.setName(name);
            product.setPrice(price);
            product.setImage(image);
            product.setCategory(category);

            return product;
        }
    }

    @Data
    public static class UpdateProduct {
        private String name;
        private Integer price;
        private String image;
        private Category category;

        public Product toProduct(Product product) {
            product.setName(name);
            product.setPrice(price);
            product.setImage(image);
            product.setCategory(category);

            return product;
        }
    }*/

}
