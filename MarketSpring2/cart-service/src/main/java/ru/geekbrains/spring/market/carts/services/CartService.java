package ru.geekbrains.spring.market.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.market.api.ProductDto;
import ru.geekbrains.spring.market.carts.Cart;
import ru.geekbrains.spring.market.carts.integrations.ProductServiceIntegration;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import static org.springframework.cloud.contract.verifier.converter.YamlContract.PredefinedRegex.uuid;


@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Objects> redisTemplate;

    @Value("${cart-service.cart-prefix}")
    private  String cartPrefix;

    public Cart getCurrantCart(String uuid) {
        String targetUuid = cartPrefix + uuid;
        if (!redisTemplate.hasKey(targetUuid)) {
            redisTemplate.opsForValue().set(targetUuid, new Cart());
        }
        return (Cart)redisTemplate.opsForValue().get(targetUuid);
    }

    public void add(String uuid, Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        execute(uuid, cart -> cart.add(product));
    }

    public void clear(String uuid) {
        execute(uuid, Cart :: clear);
    }

    public void deleteById(String uuid, Long id) {
        execute(uuid, cart -> cart.deleteById(id));
    }

    public List<ProductDto> getProductList() {
        getCurrantCart(String.valueOf(uuid)).getCartItems();
        return (List<ProductDto>)  getCurrantCart(String.valueOf(uuid));
    }

    private  void  execute(String uuid, Consumer<Cart> operation) {
        Cart cart = getCurrantCart(uuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(cartPrefix + uuid, cart);
    }
            }



