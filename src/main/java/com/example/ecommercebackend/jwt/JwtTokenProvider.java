package com.example.ecommercebackend.jwt;

import com.example.ecommercebackend.exception.JwtTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private static final String INVALID_TOKEN_MESSAGE = "Jwt token already expires or is invalid";
    @Value("${auth.jwt.expiration-ms}")
    private long validityInMs;

    @Value("${auth.jwt.secret-key}")
    private String secretKey;

    private final UserDetailsService userDetailsService;

    public String resolveBearerToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring("Bearer ".length());
        }

        return null;
    }

    public String generateJwtToken(Authentication authentication) {
        Claims claims = Jwts.claims();
        claims.setSubject(authentication.getName());
        claims.put(
                "auth",
                authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(","))
        );


        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validityInMs))
                .setSubject(authentication.getName())
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return true;
        } catch (Exception exception) {
            throw new JwtTokenException(INVALID_TOKEN_MESSAGE);
        }
    }

    public String getUsername(String jwtToken) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody().getSubject();

        } catch (Exception exception) {
            throw new JwtTokenException(INVALID_TOKEN_MESSAGE);
        }
    }

    @Transactional
    public Authentication getAuthentication(String jwtToken) {
        UserDetails userDetails
                = userDetailsService.loadUserByUsername(getUsername(jwtToken));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
