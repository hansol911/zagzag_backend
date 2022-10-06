package com.jtrio.zagzag.category;

import com.jtrio.zagzag.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
        return categories.stream().map(CategoryDTO::toDTO).collect(Collectors.toList());
    }
}
