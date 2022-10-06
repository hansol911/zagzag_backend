package com.jtrio.zagzag.category;

import com.jtrio.zagzag.model.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryCommand {
    @NotBlank
    private String name;

    public Category toCategory() {
        Category category = new Category();
        category.setName(name);
        return category;
    }
}
