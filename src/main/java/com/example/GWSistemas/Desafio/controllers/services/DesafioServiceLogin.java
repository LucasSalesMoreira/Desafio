package com.example.GWSistemas.Desafio.controllers.services;

import com.example.GWSistemas.Desafio.controllers.services.repositorys.DesafioRepositoryLogin;
import com.example.GWSistemas.Desafio.models.DesafioModelLogin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

@Service
public class DesafioServiceLogin {
    private DesafioModelLogin login;
    private String token;

    public DesafioServiceLogin() { }

    public DesafioServiceLogin(String login, String token) {
        this.token = token;
        loadJSON(login);
    }

    private void loadJSON(String jsonString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        login = gson.fromJson(jsonString, DesafioModelLogin.class);
    }

    public String login() {
        JsonObject userData = new DesafioRepositoryLogin(login).login();

        if (userData != null && !token.isEmpty()) {
            if (new DesafioServiceToken().verify(token)) {
                //liberar o acesso ao sistema.
                return "Logado!";
            } else {
                //gerar novo token de acesso (Email e senha estão corretos).
                return "Seu token expirou ou não é válido!";
            }
        } else {
            //não liberar o acesso.
            return "Email e/ou senha incorretos!";
        }

    }
}
