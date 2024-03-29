package com.jtrio.zagzag.product;

import com.jtrio.zagzag.category.CategoryRepository;
import com.jtrio.zagzag.exception.CategoryNotFoundException;
import com.jtrio.zagzag.exception.ProductNotFoundException;
import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    //상품등록
    public ProductDTO createProduct(ProductCommand command) {
        Category category = categoryRepository.findById(command.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("카테고리 없음"));
        Product product = productRepository.save(command.toProduct(category));
        return ProductDTO.toDTO(product);
    }

    //카테고리별 상품조회
    public List<ProductDTO> findProductByCategory(Long categoryId, Pageable pageable) {
        List<Product> products = productRepository.findByCategoryId(categoryId, pageable);
        return products.stream().map(ProductDTO::toDTO).collect(Collectors.toList());
    }

    //상품상세조회
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("해당 상품이 존재하지 않음"));
        return ProductDTO.toDTO(product);
    }
}