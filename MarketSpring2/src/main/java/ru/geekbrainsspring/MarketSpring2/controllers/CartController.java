package ru.geekbrainsspring.MarketSpring2.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrainsspring.MarketSpring2.dto.Cart;
import ru.geekbrainsspring.MarketSpring2.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @GetMapping("/cart/add/{id}")
    public  void  addCart(@PathVariable Long id) {
        cartService.add(id);
    }
    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrantCart();
    }
    @GetMapping("/cart/clear")
    public void clear(){
        cartService.clear();
    }
    @DeleteMapping("/delete")
    public void deleteById(Long id) {
        cartService.deleteById(id);
    }

    @GetMapping
    public int changeQuantityInCart(Long productId, int quantity) {
        return cartService.changeQuantity(productId,quantity);
    }
}
