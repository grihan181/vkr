package ru.avanesyan.vkr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.avanesyan.vkr.model.Product;
import ru.avanesyan.vkr.service.ProductService;

@RestController
@RequestMapping(value = "/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("{page}/{size}")
    public Page<Product> getProducts(@PathVariable int page, @PathVariable int size) {
        return productService.getProducts(page, size);
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
}
