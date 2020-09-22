package com.example.GWSistemas.Desafio.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DesafioControllerIndex {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> postCreate() {
        return ResponseEntity.ok("<h1>HOME PAGE</h1>");
    }
}
