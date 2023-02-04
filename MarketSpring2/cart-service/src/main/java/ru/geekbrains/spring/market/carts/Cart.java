package ru.geekbrains.spring.market.carts;

import lombok.Data;
import ru.geekbrains.spring.market.api.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> cartItems;
    private BigDecimal totalPrice;


    public Cart() {
        this.cartItems = new ArrayList<>();
    }


    private void recalculate() {
        totalPrice = BigDecimal.ZERO;
        int totalQuantity = 0;
        for (CartItem item : cartItems) {
            totalPrice = totalPrice.add(item.getPrice());
            totalQuantity += item.getQuantity();
        }
    }
    public void add(ProductDto product) {
        for(CartItem item : cartItems)
        if (product.getId().equals(item.getProductId())) {
            cartItems.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        } else {
            recalculate();
        }
        }
        public void clear() {
        cartItems.clear();
        }

    public void deleteById(Long productId) {
        if(cartItems.removeIf(cartItem -> cartItem.getProductId().equals(productId)))
        recalculate();
    }
}

