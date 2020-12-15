package com.jtrio.zagzag.product;

import com.jtrio.zagzag.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
    public final ProductService productService;

    @PostMapping
    public ProductDTO createProduct(@RequestBody @Valid ProductCommand command) {
        return productService.createProduct(command);
    }

    @GetMapping
    public List<Product> findProductByCategory(@RequestParam Long categoryId, Pageable pageable){
        return productService.findProductByCategory(categoryId, pageable);
    }

}
