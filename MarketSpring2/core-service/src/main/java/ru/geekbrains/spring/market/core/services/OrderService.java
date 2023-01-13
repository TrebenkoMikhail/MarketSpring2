package ru.geekbrains.spring.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.spring.market.api.CartDto;
import ru.geekbrains.spring.market.api.CartItemDto;
import ru.geekbrains.spring.market.core.entities.Order;
import ru.geekbrains.spring.market.core.entities.OrderItem;
import ru.geekbrains.spring.market.core.entities.User;
import ru.geekbrains.spring.market.core.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    @Transactional
    public void createOrder(User user) {
        CartDto cartDto = null;  // cartServiceIntegration.getCurrentCart();
//        cartDto = new CartDto();
//        CartItemDto cartItemDto = new CartItemDto();
//        cartItemDto.setProductId(1L);
//        cartItemDto.setQuantity(1);
//        cartItemDto.setPricePerProduct(25);
//        cartItemDto.setPrice(25);
//        cartItemDto.setProductTitle("Bread");
//        cartDto.setCartItems(List.of(cartItemDto));
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setItems(cartDto.getCartItems().stream().map(
                cartItem -> new OrderItem(
                        productService.findById(cartItem.getProductId()).get(),
                        order,
                        cartItem.getQuantity(),
                        cartItem.getPricePerProduct(),
                        cartItem.getPrice()
                        )
        ).collect(Collectors.toList()));
        orderRepository.save(order);
        // cartServiceIntegration.clear();
    }
}
