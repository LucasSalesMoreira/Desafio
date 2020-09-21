package com.example.GWSistemas.Desafio.controllers.services;

import com.example.GWSistemas.Desafio.controllers.services.repositorys.DesafioRepositoryCreateUser;
import com.example.GWSistemas.Desafio.controllers.services.repositorys.DesafioRepositoryUserVerifycation;
import com.example.GWSistemas.Desafio.models.DesafioModelUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

@Service
public class DesafioServiceCreateUser {

    private DesafioModelUser user;

    public DesafioServiceCreateUser(String user) {
        loadJSON(user);
    }

    private void loadJSON(String jsonString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        user = gson.fromJson(jsonString, DesafioModelUser.class);
    }

    public String create() {
        if (!new DesafioRepositoryUserVerifycation(user).verifyUser()) {
            if (new DesafioRepositoryCreateUser(user).create())
                return "OK";
            else
                return "ERROR";
        } else {
            return "EXIST";
        }
    }
}
