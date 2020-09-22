package com.example.GWSistemas.Desafio.controllers.services.repositorys;

import com.example.GWSistemas.Desafio.controllers.services.DesafioServiceDate;
import com.example.GWSistemas.Desafio.controllers.services.DesafioServiceSecurity;
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
            String sqlUser = "insert into user (id, nome, email, senha) values (?, ?, ?, ?)";
            String sqlTelephone = "insert into telephone (id_user, ddd, numero) values (?, ?, ?)";
            String sqlLog = "insert into log (id_user, data_criacao, data_atualizacao, data_login, token) values (?, ?, ?, ?, ?)";

            DesafioRepositoryConnection desafioConnection  = new DesafioRepositoryConnection();

            Connection conn = null;
            conn = desafioConnection.connect();
            PreparedStatement stmt = null;

            stmt = conn.prepareStatement(sqlUser);
            DesafioServiceSecurity security = new DesafioServiceSecurity();

            String id = security.generateID();

            stmt.setString(1, id);
            stmt.setString(2, user.getNome());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, security.encrypt(user.getSenha()));
            stmt.executeUpdate();
            desafioConnection.finish(conn, stmt);

            for (int i = 0; i < user.getTelefones().size(); i++) {
                conn = desafioConnection.connect();
                stmt = conn.prepareStatement(sqlTelephone);
                stmt.setString(1, id);
                stmt.setString(2, user.getTelefones().get(i).getAsJsonObject().get("ddd").getAsString());
                stmt.setString(3, user.getTelefones().get(i).getAsJsonObject().get("numero").getAsString());
                stmt.executeUpdate();
                desafioConnection.finish(conn, stmt);
            }

            PreparedStatement stmtLog = conn.prepareStatement(sqlLog);
            stmtLog.setString(2, DesafioServiceDate.getDate());
            stmtLog.setString(3, DesafioServiceDate.getDate());
            stmtLog.setString(4, "Nunca");
            stmtLog.setString(5, "token");

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
