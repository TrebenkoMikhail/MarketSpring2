package ru.gb.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.SpringBootTestBase;
import ru.gb.model.Customer;
import ru.gb.repository.CustomerRepository;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class CustomerControllerTest extends SpringBootTestBase {
    @Autowired
    CustomerRepository repository;
   @Autowired
    WebTestClient webTestClient;
   @Test
    void testFindById(){
       Customer customer = new Customer();
       customer.setName("TestCustomer");
       customer = repository.save(customer);
       Customer customerByHttp = webTestClient.get()
               .uri("/customer/" + customer.getId())
               .exchange()
               .expectStatus().isOk()
               .expectBody(Customer.class)
               .returnResult()
               .getResponseBody();

       Assertions.assertEquals(customer.getId(), customerByHttp.getId());
       Assertions.assertEquals(customer.getName(), customerByHttp.getName());
//       ResponseEntity<Customer> testCustomer = customerController.create("TestCustomer");
   }

   @Test
    void testFindByIdNotFound() {
       repository.deleteAll();
       webTestClient.get()
               .uri("/customer/1")
               .exchange()
               .expectStatus().isNotFound();
   }
}