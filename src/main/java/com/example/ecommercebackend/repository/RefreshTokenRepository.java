package com.example.ecommercebackend.repository;

import com.example.ecommercebackend.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(@Param("token") String token);
    void deleteByToken(@Param("token") String token);
}
