package com.example.GWSistemas.Desafio.controllers.services;

import com.example.GWSistemas.Desafio.controllers.services.repositorys.DesafioRepositoryLogin;
import com.example.GWSistemas.Desafio.controllers.services.repositorys.DesafioRepositoryTokenStatus;
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

        String id = new DesafioRepositoryLogin(login).login();

        if (!id.equals("ERROR")) {
            JsonObject tokenStatus = new DesafioServiceToken().verifyToken(token, id, login.getEmail());
            String status = tokenStatus.get("status_token").getAsString();

            if (status.equals(DesafioRepositoryTokenStatus._VALID_TOKEN)) {
                //Liberar o acesso ao sistema (retornar objeto de usuário).
                return tokenStatus.toString();

            } else if (status.equals(DesafioRepositoryTokenStatus._UPDATED_BY_EXPIRATION)) {
                //Novo token gerado e persistido (o anterior estava expirado).
                return tokenStatus.toString();

            } else if (status.equals(DesafioRepositoryTokenStatus._UPDATED_BY_INVALIDATION)) {
                //Novo token gerado e persistido (O anterior não pode ser decodificado).
                return tokenStatus.toString();

            } else if (status.equals(DesafioRepositoryTokenStatus._INCORRECT_TOKEN)) {
                //Esse token não pertence a este user.
                return tokenStatus.toString();

            } else if (status.equals(DesafioRepositoryTokenStatus._ERROR)) {
                return "Falha!";
            }
        } else {
            //não liberar o acesso.
            return "Email e/ou senha incorretos!";
        }

        return null;
    }
}
