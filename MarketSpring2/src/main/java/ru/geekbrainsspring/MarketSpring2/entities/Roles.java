package ru.geekbrainsspring.MarketSpring2.entities;



import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.*;


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
