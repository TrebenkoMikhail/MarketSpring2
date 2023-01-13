package ru.geekbrains.spring.market.core.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Table;

@Entity
@Data
@Table(appliesTo = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
