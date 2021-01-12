package com.jtrio.zagzag.category;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public CategoryDTO createCategory(CategoryCommand command) {
        return categoryService.createCategory(command);
    }

    @GetMapping
    @Cacheable(cacheNames = "categories")
    public List<CategoryDTO> readCategory() {
        return categoryService.readCategory();
    }
}
