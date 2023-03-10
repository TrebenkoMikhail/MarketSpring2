package ru.geekbrainsspring.MarketSpring2.entities;


import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Data
@Table(appliesTo = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;


    @ManyToMany
    @JoinTable(name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Roles> roles;


}
