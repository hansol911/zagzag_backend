package com.jtrio.zagzag.category;

import com.jtrio.zagzag.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryDTO createCategory(CategoryCommand command) {
        Category category = categoryRepository.save(command.toCategory());
        return CategoryDTO.toDTO(category);
    }

    public List<CategoryDTO> readCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category c : categories) {
            CategoryDTO dto = CategoryDTO.toDTO(c);
            categoryDTOS.add(dto);
        }
        return categoryDTOS;
    }
}
