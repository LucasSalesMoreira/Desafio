package com.example.GWSistemas.Desafio.controllers.services;

import com.example.GWSistemas.Desafio.controllers.services.repositorys.DesafioRepositoryTokenStatus;
import com.example.GWSistemas.Desafio.controllers.services.repositorys.DesafioRepositoryTokenUpdate;
import com.google.gson.JsonObject;
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

    public JsonObject verifyToken(String token, String id, String email) {

        JsonObject status = new JsonObject();

        try {
            String tokenTreated = token.replace("Bearer ", "");
            //Tentando decriptar e validar
            Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(tokenTreated).getBody();

            System.out.println("URL: " + claims.getIssuer());
            System.out.println("Data de criação: " + claims.getIssuedAt());
            System.out.println("Proprietário: " + claims.getSubject());
            System.out.println("Data de expiração: " + claims.getExpiration());

            if (claims.getSubject().equals(email)) {
                status.addProperty("status_token", DesafioRepositoryTokenStatus._VALID_TOKEN);
                status.addProperty("url", claims.getIssuer());
                status.addProperty("data_criacao", DesafioServiceDate.format(claims.getIssuedAt()));
                status.addProperty("proprietario", claims.getSubject());
                status.addProperty("data_expiracao", DesafioServiceDate.format(claims.getExpiration()));
                status.addProperty("token", tokenTreated);
            } else {
                status.addProperty("status_token", DesafioRepositoryTokenStatus._INCORRECT_TOKEN);
            }

            return status;

        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado!");
            String newToken = generateToken(email);
            if (new DesafioRepositoryTokenUpdate().update(id, newToken)) {
                status.addProperty("status_token", DesafioRepositoryTokenStatus._UPDATED_BY_EXPIRATION);
                status.addProperty("id_user", id);
                status.addProperty("novo_token", newToken);
                return status;
            }

        } catch (SignatureException e) {
            System.out.println("Token inválido!");
            String newToken = generateToken(email);
            if (new DesafioRepositoryTokenUpdate().update(id, newToken)) {
                status.addProperty("status_token", DesafioRepositoryTokenStatus._UPDATED_BY_INVALIDATION);
                status.addProperty("id_user", id);
                status.addProperty("novo_token", newToken);
                return status;
            }
        }

        status.addProperty("status_token", DesafioRepositoryTokenStatus._ERROR);

        return status;
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
