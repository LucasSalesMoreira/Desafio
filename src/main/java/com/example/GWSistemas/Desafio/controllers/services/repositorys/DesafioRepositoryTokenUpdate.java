package com.example.GWSistemas.Desafio.controllers.services.repositorys;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class DesafioRepositoryTokenUpdate {

    public boolean update(String id, String token) {

        try {
            String sql = "update log set token = ? where id_user = ?";
            DesafioRepositoryConnection desafioConnection = new DesafioRepositoryConnection();
            Connection conn = desafioConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, token);
            stmt.setString(2, id);
            stmt.executeUpdate();
            desafioConnection.finish(conn, stmt);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}