package com.example.ecommercebackend.model.entity;


import lombok.*;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "country", cascade = ALL)
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "country", cascade = ALL)
    private Set<State> states = new HashSet<>();
}
