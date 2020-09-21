package com.example.GWSistemas.Desafio.controllers;

import com.example.GWSistemas.Desafio.controllers.services.DesafioServiceCreateUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DesafioControllerCreateUser {

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> postCreate(@RequestParam("myJsonEncoded") String jsonString) {

        String status = new DesafioServiceCreateUser(jsonString).create();

        if (status.equals("OK"))
            return ResponseEntity.ok("Cadastro realizado!");
        else if (status.equals("EXIST"))
            return ResponseEntity.ok("E-mail já existente!");
        else if (status.equals("ERROR"))
            return ResponseEntity.ok("Falha no cadastro!");
        else
            return null;
    }
}