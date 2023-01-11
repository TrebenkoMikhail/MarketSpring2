package ru.geekbrainsspring.MarketSpring2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrainsspring.MarketSpring2.entities.User;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;

    public void createOrder(User user) {

    }
}
