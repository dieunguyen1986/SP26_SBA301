package edu.lms.sercurity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());

    }

    public String generateAccessToken(UserDetails userDetails) {
       return Jwts.builder().subject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities().stream()
                        .map((grantedAuthority) -> grantedAuthority.getAuthority()).collect(Collectors.toList()))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSecretKey()).compact();
    }
}
