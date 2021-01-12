package com.jtrio.zagzag.category;

import com.jtrio.zagzag.model.Category;
import lombok.Data;

@Data
public class CategoryDTO {
    private String name;

    public static CategoryDTO toDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }
}
