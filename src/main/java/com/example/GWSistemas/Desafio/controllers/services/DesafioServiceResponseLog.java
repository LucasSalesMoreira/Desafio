package com.example.GWSistemas.Desafio.controllers.services;

import com.example.GWSistemas.Desafio.controllers.services.repositorys.DesafioRepositoryResponseLog;
import com.example.GWSistemas.Desafio.models.DesafioModelUser;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

@Service
public class DesafioServiceResponseLog {
    public JsonObject generateResponse(DesafioModelUser user) {

        JsonObject log = new DesafioRepositoryResponseLog().getResponseLog(user.getEmail());
        log.addProperty("nome", user.getNome());
        log.addProperty("email", user.getEmail());
        log.addProperty("senha", new DesafioServiceSecurity().encrypt(user.getSenha()));
        log.add("telefones", user.getTelefones());

        return log;
    }
}
