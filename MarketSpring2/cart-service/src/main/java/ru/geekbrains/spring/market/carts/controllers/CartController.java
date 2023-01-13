package ru.geekbrains.spring.market.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.market.api.CartDto;
import ru.geekbrains.spring.market.carts.converters.CartConverter;
import ru.geekbrains.spring.market.carts.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;
    @GetMapping("/cart/add/{id}")
    public  void  addCart(@PathVariable Long id) {
        cartService.add(id);
    }
    @GetMapping
    public CartDto getCurrentCart() {
        return cartConverter.entityToDto(cartService.getCurrantCart());
    }
    @GetMapping("/cart/clear")
    public void clear(){
        cartService.clear();
    }
    @DeleteMapping("/delete")
    public void deleteById(Long id) {
        cartService.deleteById(id);
    }

}
