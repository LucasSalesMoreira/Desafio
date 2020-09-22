package com.example.GWSistemas.Desafio.controllers.services;

import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class DesafioServiceSecurity {

    public String encrypt(String text) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            byte hash[] = algorithm.digest(text.getBytes("UTF-8"));
            StringBuilder builder = new StringBuilder();

            for (byte b : hash) {
                builder.append(String.format("%02X", 0xFF & b));
            }

            String hashText = builder.toString();
            System.out.println("Texto criptografado: " + hashText);

            return hashText;

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String generateID() {
        return UUID.randomUUID().toString();
    }
}
