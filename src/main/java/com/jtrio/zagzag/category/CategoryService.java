package com.jtrio.zagzag.category;

import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.product.ProductDTO;
import com.jtrio.zagzag.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryDTO.CreateCategory createCategory(CategoryCommand command) {
        Category category = categoryRepository.save(command.toCategory());
        return CategoryDTO.CreateCategory.toDTO(category);
    }

    public List<CategoryDTO.ReadCategory> readCategory(Pageable pageable) {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO.ReadCategory> categoryDTOS = new ArrayList<>();
        categories.forEach(c -> {
            List<Product> products = productRepository.findByCategoryId(c.getId(), pageable);
            CategoryDTO.ReadCategory dto = CategoryDTO.ReadCategory.toDTO(c, products);
            categoryDTOS.add(dto);});
        return categoryDTOS;
    }
}
