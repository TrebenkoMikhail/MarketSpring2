package ru.geekbrains.spring.market.carts.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.spring.market.api.OrderItemDto;
import ru.geekbrains.spring.market.core.entities.OrderItem;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setPricePerProduct(orderItem.getPricePerProduct());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setProductTitle(orderItem.getProduct().getTitle());
        return orderItemDto;
    }
}
