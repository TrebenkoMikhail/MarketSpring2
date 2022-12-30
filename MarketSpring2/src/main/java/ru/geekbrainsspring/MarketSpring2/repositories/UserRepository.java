package ru.geekbrainsspring.MarketSpring2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrainsspring.MarketSpring2.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);

    Optional<org.apache.catalina.User> findByUsername(String username);
}
