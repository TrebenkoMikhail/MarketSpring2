package ru.gb.servecies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.gb.SpringBootTestBase;
import ru.gb.model.Customer;
import ru.gb.repository.CustomerRepository;
@ActiveProfiles("test")
@SpringBootTest
class CustomerServiceTest extends SpringBootTestBase {

    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;
    @Test
    void create() {
        Customer createdCustomer = customerService.create("TestCustomer");
        Assertions.assertNotNull(createdCustomer);
        Assertions.assertNotNull(createdCustomer.getId());
        Assertions.assertEquals("TestCustomer", createdCustomer.getName());

        Customer savedCustomer = customerRepository.findById(createdCustomer.getId()).orElse(null);
        Assertions.assertEquals("TestCustomer", savedCustomer.getName());

    }

    @Test
    void findById() {
    }

}