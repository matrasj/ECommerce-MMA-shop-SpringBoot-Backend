package com.example.ecommercebackend.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String resolveBearerToken = jwtTokenProvider.resolveBearerToken(request);
        try {
            if (resolveBearerToken != null && jwtTokenProvider.validateToken(resolveBearerToken)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(resolveBearerToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception exception) {
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
