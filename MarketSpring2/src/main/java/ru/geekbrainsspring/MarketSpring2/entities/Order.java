package ru.geekbrainsspring.MarketSpring2.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String phone;
    private Date date = new Date();
        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Product> productList = new ArrayList<>();

}
