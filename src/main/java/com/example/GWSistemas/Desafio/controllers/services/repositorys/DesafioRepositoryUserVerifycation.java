package com.example.GWSistemas.Desafio.controllers.services.repositorys;

import com.example.GWSistemas.Desafio.models.DesafioModelUser;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DesafioRepositoryUserVerifycation {

    private DesafioModelUser user;

    public DesafioRepositoryUserVerifycation(DesafioModelUser user) {
        this.user = user;
    }

    public boolean verifyUser() {
        try {
            String sql = "select * from user where email = ?";
            Connection conn = new DesafioRepositoryConnection().connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
