package com.ecommerce.security.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtService {


    private final SecretKey key = Jwts.SIG.HS256.key().build();


    public SecretKey getKey() {
        return key;
    }

    public String generateToken(String username) {

        Date date = new Date();

        return Jwts.builder()
                .signWith(getKey())
                .subject(username)
                .issuedAt(date)
                .expiration(new Date(date.getTime() +  10 * 60 * 1000))
                .compact();
    }


    public String getUsernameByToken(String token) {

        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

}
