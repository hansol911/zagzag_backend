package com.jtrio.zagzag.product;

import com.jtrio.zagzag.category.CategoryRepository;
import com.jtrio.zagzag.exception.CategoryNotFoundException;
import com.jtrio.zagzag.exception.UserNotFoundException;
import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    //상품등록
    public ProductDTO registeredProduct(ProductCommand command) {
        Category category = categoryRepository.findById(command.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("카테고리 없음"));
        Product product = productRepository.save(command.toProduct(category));
        return product.toDTO();
    }

    //카테고리별 상품조회
    public List<Product> findProductByCategory(Long categoryId){
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return products;
    }

}
