package ru.geekbrainsspring.MarketSpring2.dto;

import lombok.Data;
import ru.geekbrainsspring.MarketSpring2.entities.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> cartItems;
    private int totalPrice;
    private int totalQuantity;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public List<CartItem> getCartItems() {
        return Collection.unmodifiableList(cartItems);
    }

    private void recalculate() {
        totalPrice = 0;
        totalQuantity = 0;
        for (CartItem item : cartItems) {
            totalPrice += item.getPrice();
            totalQuantity += item.getQuantity();
        }
    }
    public void add(Product product) { // TODO: доработать в дз
        if (cartItems.isEmpty()) {
            cartItems.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        } else {
            recalculate();
        }
        }
        public void clear() {
        cartItems.clear();
        }

    public List<Product> deleteById(Long id) {
        cartItems.
    }
}

