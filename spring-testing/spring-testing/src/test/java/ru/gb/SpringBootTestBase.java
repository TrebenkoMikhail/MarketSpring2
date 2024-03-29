package ru.gb;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class SpringBootTestBase {
    static PostgreSQLContainer<?> container;
    static {
        container = new PostgreSQLContainer<>("postgres:11.1")
                .withDatabaseName("test-db")
                .withUsername("junit")
                .withPassword("junit");

                container.start(); // docker run postgres:11.1
    }

    @DynamicPropertySource
    static void dynamicPropertySource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> container.getJdbcUrl());
        registry.add("spring.datasource.username", () -> container.getUsername());
        registry.add("spring.datasource.password", () -> container.getPassword());
        registry.add("spring.datasource.driver-class-name", () -> container.getDriverClassName());
    }
}
