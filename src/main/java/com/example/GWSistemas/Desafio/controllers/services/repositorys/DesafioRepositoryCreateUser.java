package com.example.GWSistemas.Desafio.controllers.services.repositorys;

import com.example.GWSistemas.Desafio.models.DesafioModelUser;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class DesafioRepositoryCreateUser {

    private DesafioModelUser user;

    public DesafioRepositoryCreateUser(DesafioModelUser user) {
        this.user = user;
    }

    public boolean create() {
        try {
            String sql = "insert into user () values ()";
            Connection conn = new DesafioRepositoryConnection().connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
