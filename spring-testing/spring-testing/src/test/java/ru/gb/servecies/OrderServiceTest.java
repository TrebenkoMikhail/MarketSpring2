package ru.gb.servecies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import ru.gb.SpringBootTestBase;
import ru.gb.model.Customer;
import ru.gb.model.Order;
import ru.gb.repository.CustomerRepository;
import ru.gb.repository.OrderRepository;

import java.util.NoSuchElementException;
//@SpringBootTest(classes = OrderServiceTest.OrderNumberServiceTestConfiguration.class)
@SpringBootTest
class OrderServiceTest extends SpringBootTestBase {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
//    @SpyBean
//    OrderNumberService orderNumberService;

    @TestConfiguration
    @Primary
    static class OrderNumberServiceTestConfiguration {
        @Bean
        public OrderNumberService testOrderNumberService(){
            return new OrderNumberService() {
                @Override
                public String next() {
                    return "0";
                }
            };
        }
    }
    @Test
    void testCreateOrder() {
        var customer = new Customer();
        customer.setName("TestCustomer");
        customer = customerRepository.save(customer);

//        Mockito.when(orderNumberService.next()).thenReturn("TestNumber");

        Order createdOrder = orderService.create(customer.getId());
        Assertions.assertNotNull(createdOrder);
        Assertions.assertNotNull(createdOrder.getId());
        Assertions.assertEquals(customer.getId(), createdOrder.getCustomerId());
        Assertions.assertEquals( "TestNumber", createdOrder.getNumber());

        Order savedOrder = orderRepository.findById(createdOrder.getId()).orElse(null);
        Assertions.assertEquals(createdOrder.getId(), savedOrder.getId());
        Assertions.assertEquals(createdOrder.getCustomerId(), savedOrder.getCustomerId());
        Assertions.assertEquals(createdOrder.getNumber(), savedOrder.getNumber());


    }

    @Test
    void createOrderCustomerNotFound() {
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> orderService.create(-1L))
        .isInstanceOf(NoSuchElementException.class);

//        Mockito.verifyNoInteractions(orderNumberService);
//        Mockito.verify(orderNumberService, Mockito.times(0)).next();
    }
}