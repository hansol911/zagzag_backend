package com.jtrio.zagzag.category;

import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.product.ProductDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDTO {
    @Data
    public static class CreateCategory {
        private String name;

        public static CategoryDTO.CreateCategory toDTO(Category category) {
            CategoryDTO.CreateCategory categoryDTO = new CategoryDTO.CreateCategory();
            categoryDTO.setName(category.getName());
            return categoryDTO;
        }
    }

    @Data
    public static class ReadCategory {
        private String name;
        private List<ProductDTO> productDTOS;

        public static CategoryDTO.ReadCategory toDTO(Category category, List<Product> products) {
            CategoryDTO.ReadCategory categoryDTO = new CategoryDTO.ReadCategory();
            List<ProductDTO> pdtos = new ArrayList<>();
            for (Product p : products) {
                ProductDTO dto = ProductDTO.toDTO(p);
                pdtos.add(dto);
            }
            categoryDTO.setName(category.getName());
            categoryDTO.setProductDTOS(pdtos);
            return categoryDTO;
        }
    }

}
