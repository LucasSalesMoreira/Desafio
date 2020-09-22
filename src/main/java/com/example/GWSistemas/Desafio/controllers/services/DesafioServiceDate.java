package com.example.GWSistemas.Desafio.controllers.services;

import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DesafioServiceDate {

    public static String getDate() {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date(System.currentTimeMillis()));
    }
}
