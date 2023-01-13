package ru.geekbrains.spring.market.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.market.api.ProductDto;
import ru.geekbrains.spring.market.api.ResourceNotFoundException;
import ru.geekbrains.spring.market.carts.Cart;
import ru.geekbrains.spring.market.carts.integrations.ProductServiceIntegration;
import javax.annotation.PostConstruct;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrantCart() {
        return tempCart;
    }

    public void add(Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        tempCart.add(product);
    }

    public void clear() {
        tempCart.clear();
    }

    public void deleteById(Long id) {
        tempCart.deleteById(id);
    }

    public List<ProductDto> getProductList() {
        tempCart.getCartItems();
        return (List<ProductDto>) tempCart;
    }
            }



