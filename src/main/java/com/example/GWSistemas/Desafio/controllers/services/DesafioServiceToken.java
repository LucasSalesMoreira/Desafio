package com.example.GWSistemas.Desafio.controllers.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class DesafioServiceToken {

    private static final SecretKey KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static final int EXPIRATION_TIME = 120000; // 2 minutos.

    public boolean verify(String token) {

        try {
            String tokenTreated = token.replace("Bearer ", "");
            Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
            System.out.println(claims.getIssuer());
            System.out.println(claims.getIssuedAt());
            System.out.println(claims.getSubject());
            System.out.println(claims.getExpiration());
            return true;

        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado!");
        } catch (SignatureException e) {
            System.out.println("Token inválido!");
        }

        return false;
    }

    public String generateToken(String email) {

        return Jwts.builder()
                .setIssuer("http://localhost:8082")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY)
                .compact();
    }
}
