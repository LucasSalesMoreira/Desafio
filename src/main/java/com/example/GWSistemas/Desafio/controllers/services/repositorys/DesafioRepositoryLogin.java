package com.example.GWSistemas.Desafio.controllers.services.repositorys;

import com.example.GWSistemas.Desafio.controllers.services.DesafioServiceSecurity;
import com.example.GWSistemas.Desafio.models.DesafioModelLogin;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DesafioRepositoryLogin {

    private DesafioModelLogin login;

    public DesafioRepositoryLogin() {}

    public DesafioRepositoryLogin(DesafioModelLogin login) {
        this.login = login;
    }

    public JsonObject login() {
        try {
            String sql = "select * from user where email = ? and senha = ?";
            DesafioRepositoryConnection desafioConnection = new DesafioRepositoryConnection();
            Connection conn = desafioConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, login.getEmail());
            stmt.setString(2, new DesafioServiceSecurity().encrypt(login.getSenha()));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JsonObject userData = new JsonObject();
                userData.addProperty("id", rs.getString("id"));
                //userData.addProperty("token", rs.getString("token"));
                return userData;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
