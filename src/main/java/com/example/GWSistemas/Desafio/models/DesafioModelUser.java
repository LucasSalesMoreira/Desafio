package com.example.GWSistemas.Desafio.models;

import com.google.gson.JsonArray;

public class DesafioModelUser {
    private String nome, email, senha;
    private JsonArray telefones;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public JsonArray getTelefones() {
        return telefones;
    }

    public void setTelefones(JsonArray telefones) {
        this.telefones = telefones;
    }
}
