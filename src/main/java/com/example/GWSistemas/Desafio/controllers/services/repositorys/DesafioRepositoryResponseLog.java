package com.example.GWSistemas.Desafio.controllers.services.repositorys;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DesafioRepositoryResponseLog {

    private String getIdFromDB(String email) {
        try {
            String sql = "select id from user where email = ?";
            DesafioRepositoryConnection desafioConnection = new DesafioRepositoryConnection();
            Connection conn = desafioConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                return rs.getString("id");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public JsonObject getResponseLog(String email) {
        try {
            String sql = "select * from log where id_user = ?";
            DesafioRepositoryConnection desafioConnection = new DesafioRepositoryConnection();
            Connection conn = desafioConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, getIdFromDB(email));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JsonObject log = new JsonObject();
                log.addProperty("id", rs.getString("id_user"));
                log.addProperty("data_criacao", rs.getString("data_criacao"));
                log.addProperty("data_atualizacao", rs.getString("data_atualizacao"));
                log.addProperty("data_login", rs.getString("data_login"));
                log.addProperty("token", rs.getString("token"));
                return log;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
