package ru.geekbrainsspring.MarketSpring2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrainsspring.MarketSpring2.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product,Long> {
}
