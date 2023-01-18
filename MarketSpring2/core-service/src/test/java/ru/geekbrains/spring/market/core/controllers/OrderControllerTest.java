package ru.geekbrains.spring.market.core.controllers;

import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.spring.market.api.CartItemDto;
import ru.geekbrains.spring.market.core.entities.Order;
import ru.geekbrains.spring.market.core.entities.OrderItem;
import ru.geekbrains.spring.market.core.integrations.CartServiceIntegration;
import ru.geekbrains.spring.market.core.repositories.OrderRepository;
import ru.geekbrains.spring.market.core.services.OrderService;

import java.util.NoSuchElementException;
@SpringBootTest
class OrderControllerTest {

          @Autowired
    OrderService orderService;

        @Autowired
        OrderController orderController;
        @Autowired
        OrderRepository orderRepository;
        @MockBean
        OrderItem orderItem;

        @Test
        void testCreateOrder() {
            var order = new Order();
            order.setUsername("TestUsername");
            order = orderRepository.save(order);

            Mockito.when(orderItem.getOrder()).thenThrow();

            Order createdOrder = orderService.createOrder(order.getUsername());
            Assertions.assertNotNull(createdOrder);
            Assertions.assertNotNull(createdOrder.getUsername());
            Assertions.assertEquals(order.getUsername(), createdOrder.getUsername());


            Order savedOrder = orderRepository.findById(createdOrder.getId()).orElse(null);
            org.assertj.core.api.Assertions.assertThat(createdOrder)
                    .returns(savedOrder.getId(), Order::getId)
                    .returns(savedOrder.getUsername(), Order::getUsername)
                    .returns(savedOrder.getAddress(), Order::getAddress);

        }

        @Test
        void testCreateOrderCustomerNotFound() {
            org.assertj.core.api.Assertions.assertThatThrownBy(() -> orderController.createOrder("TestUsername"))
                    .isInstanceOf(NoSuchElementException.class);

            Mockito.verifyNoInteractions(orderController);

        }

    }
