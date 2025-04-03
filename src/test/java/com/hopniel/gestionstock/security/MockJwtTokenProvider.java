package com.hopniel.gestionstock.security;

import org.springframework.boot.test.context.TestComponent;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@TestComponent
public class MockJwtTokenProvider {

    public String createToken(Authentication authentication) {
        return "mock-jwt-token";
    }
    
    public Authentication getAuthentication(String token) {
        User principal = new User("testuser", "", 
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        return new UsernamePasswordAuthenticationToken(principal, token, principal.getAuthorities());
    }
    
    public boolean validateToken(String token) {
        return true;
    }
    
    public String getUsernameFromToken(String token) {
        return "testuser";
    }
}
