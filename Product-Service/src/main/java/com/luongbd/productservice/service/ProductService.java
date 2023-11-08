package com.luongbd.productservice.service;

import com.luongbd.productservice.dto.request.ProductRequest;
import com.luongbd.productservice.dto.response.ProductResponse;
import com.luongbd.productservice.model.Product;
import com.luongbd.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public Object createProduct(ProductRequest request) {
        Product product = Product.builder()
            .name(request.getName())
            .price(request.getPrice())
            .description(request.getDescription())
            .build();

        product = productRepository.save(product);
        log.info("Product {} is saved", product.getId());
        return productRepository.getProductById(product.getId());
    }

    public Object getAllProducts(Pageable pageable) {
        List<ProductResponse> response = productRepository.getAllProducts(pageable);
        log.info("Get all products {}", response);
        return response;
    }
}
