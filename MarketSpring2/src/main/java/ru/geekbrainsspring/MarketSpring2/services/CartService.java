package ru.geekbrainsspring.MarketSpring2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrainsspring.MarketSpring2.dto.Cart;
import ru.geekbrainsspring.MarketSpring2.dto.CartItem;
import ru.geekbrainsspring.MarketSpring2.entities.Product;
import ru.geekbrainsspring.MarketSpring2.exceptions.ResourceNotFoundException;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private Cart tempCart;
    private CartItem cartItem;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrantCart() {
        return tempCart;
    }

    public void add(Long productId) {
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Не удаётся добавить продукт с id: " + productId + " в корзину. Продукт не найден"));
        tempCart.add(product);
    }

    public void clear() {
        tempCart.clear();
    }

    public List<Product> deleteById(Long id) {
        return tempCart.deleteById(id);
    }

    public List<Product> getProductList() {
        tempCart.getCartItems();
        return (List<Product>) tempCart;
    }

    public int changeQuantity(Long productId, int quantity) {
       Integer newQuantity = null;
        for (CartItem item : tempCart.getCartItems())
            if (tempCart.getCartItems().equals(productId)) {
                if (quantity >= 0) {
                    newQuantity = 0;
                    quantity = newQuantity;
                }
                newQuantity += item.getQuantity();
            }
        return cartItem.getQuantity();

                }
            }



