package ru.geekbrains.spring.market.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.market.api.CartDto;
import ru.geekbrains.spring.market.api.StringResponse;
import ru.geekbrains.spring.market.carts.converters.CartConverter;
import ru.geekbrains.spring.market.carts.services.CartService;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/generate_uuid")
    public StringResponse generateUuid() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{uuid}/add/{id}")
    public  void  addCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @PathVariable Long id) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.add(targetUuid, id);
    }
    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(@RequestHeader(name = "username", required = false) String username,@PathVariable String uuid) {
        String targetUuid = getCartUuid(username, uuid);
        return cartConverter.entityToDto(cartService.getCurrantCart(targetUuid));
    }
    @GetMapping("/{uuid}/clear")
    public void clear(@RequestHeader(name = "username", required = false) String username,@PathVariable String uuid){
        String targetUuid = getCartUuid(username, uuid);
        cartService.clear(targetUuid);
    }
    @DeleteMapping("/{uuid}/delete")
    public void deleteById(@RequestHeader(name = "username", required = false) String username,@PathVariable String uuid, Long id) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.deleteById(targetUuid, id);
    }

    private String getCartUuid(String username, String uuid) {
        if (username != null) {
            return username;
        }
        return uuid;
    }
}
