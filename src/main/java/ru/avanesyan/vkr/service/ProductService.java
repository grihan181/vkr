package ru.avanesyan.vkr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.avanesyan.vkr.model.Product;
import ru.avanesyan.vkr.model.Provider;
import ru.avanesyan.vkr.repo.ProductRepository;

import java.util.Random;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Page<Product> getProducts(int page, int size) {
       return productRepository.findAll(PageRequest.of(page, size));
    }
}