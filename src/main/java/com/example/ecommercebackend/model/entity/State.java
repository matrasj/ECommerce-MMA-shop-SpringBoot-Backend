package com.example.ecommercebackend.model.entity;

import lombok.*;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "state")
public class State {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "state", cascade = ALL)
    private Set<Address> addresses = new HashSet<>();

    @ManyToOne(cascade = {
            DETACH,
            MERGE,
            PERSIST,
            REFRESH
    })
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;
}
