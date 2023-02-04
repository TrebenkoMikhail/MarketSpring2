package ru.geekbrains.spring.market.carts.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.market.api.CartDto;
import ru.geekbrains.spring.market.api.OrderDto;
import ru.geekbrains.spring.market.carts.Cart;
import ru.geekbrains.spring.market.core.entities.Order;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;
    public OrderDto entityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setAddress(order.getAddress());
        orderDto.setPhone(order.getPhone());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setUsername(order.getUsername());
        orderDto.setItems(order.getItems().stream().map(orderItemConverter :: entityToDto).collect(Collectors.toList()));
        return orderDto;
    }
}
