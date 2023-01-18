package ru.geekbrains.spring.market.services;

import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import ru.geekbrains.spring.market.entities.User;
import ru.geekbrains.spring.market.repositories.UserRepository;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@SpringBootTest
@AutoConfigureWebTestClient
public class UserServiceTest extends TestCase {
    @Autowired
    UserRepository userRepository;
    @Autowired
    WebTestClient webTestClient;
    public void testFindByUsername(User username) {
        User user = new User();
        user.setUsername("TestCustomer");
        user = userRepository.save(username);
        User userByHttp = webTestClient.get()
                .uri("/customer/" + user.getUsername())
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();

       Assertions.assertEquals(user.getId(), userByHttp.getId());
        Assertions.assertEquals(user.getUsername(), userByHttp.getUsername());

    }
}