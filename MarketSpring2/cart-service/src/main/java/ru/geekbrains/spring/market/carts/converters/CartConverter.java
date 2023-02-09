package ru.geekbrains.spring.market.carts.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.market.api.CartDto;
import ru.geekbrains.spring.market.carts.Cart;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;
    public CartDto entityToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setTotalPrice(cart.getTotalPrice());
        cartDto.setCartItems(cart.getCartItems().stream().map(cartItemConverter :: entityToDto).collect(Collectors.toList()));
        return cartDto;
    }
}
