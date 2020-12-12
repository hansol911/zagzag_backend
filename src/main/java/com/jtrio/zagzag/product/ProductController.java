package com.jtrio.zagzag.product;

import com.jtrio.zagzag.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
    public final ProductService productService;

    @PostMapping
    public ProductDTO registeredProduct(@RequestBody ProductCommand command) {
        return productService.registeredProduct(command);
    }

    @GetMapping
    public List<Product> findProductByCategory(@RequestParam Long categoryId){
        return productService.findProductByCategory(categoryId);
    }

}
