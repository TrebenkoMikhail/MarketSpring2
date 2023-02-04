package ru.geekbrains.spring.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.geekbrains.spring.market.api.CartDto;



@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartserviceWebClient;

    public CartDto getCurrentCart(String username) {
        return cartserviceWebClient.get()
                .uri("/api/v1/cart/0")
                .header("username", username)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }
    public void clear(String username) {
        cartserviceWebClient.get()
                .uri("/api/v1/cart/0/clear")
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
