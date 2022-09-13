package com.example.ecommercebackend.repository;

import com.example.ecommercebackend.model.entity.Country;
import com.example.ecommercebackend.model.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    List<State> findAllByCountryName(@Param("countryName") String countryName);
    Optional<State> findByName(@Param("name") String name);
}
