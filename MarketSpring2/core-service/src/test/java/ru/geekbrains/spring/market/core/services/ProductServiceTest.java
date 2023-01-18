package ru.geekbrains.spring.market.core.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.geekbrains.spring.market.api.ProductDto;
import ru.geekbrains.spring.market.core.entities.Product;
import ru.geekbrains.spring.market.core.repositories.ProductRepository;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

    @Test
    void createNewProduct(ProductDto testProduct) {
        Product createdProduct = productService.createNewProduct(testProduct);
        testProduct.setTitle("TestProduct");
        Assertions.assertNotNull(createdProduct);
        Assertions.assertNotNull(createdProduct.getId());
        Assertions.assertEquals("TestProduct", createdProduct.getTitle());

        Product savedProduct = productRepository.findById(createdProduct.getId()).orElse(null);
        Assertions.assertEquals("TestProduct", savedProduct.getTitle());
    }
}