package ru.geekbrains.spring.market.api;


import java.util.List;

public class CartDto {
    private List<CartItemDto> cartItems;
    private int totalPrice;

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}

