package com.example.GWSistemas.Desafio.controllers;

import com.example.GWSistemas.Desafio.controllers.services.DesafioServiceLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DesafioControllerLogin {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> postLogin(@RequestBody String accessData, @RequestHeader String authorization) {

        String response = new DesafioServiceLogin(accessData, authorization).login();

        return ResponseEntity.ok(response);
    }
}
