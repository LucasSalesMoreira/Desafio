package com.example.GWSistemas.Desafio.controllers.services.repositorys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DesafioRepositoryConnection {
    private static final String HOST = "jdbc:mysql://localhost:3306/desafiobd";
    private static final String USER = "root";
    private static final String PASS = null;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public Connection connect() {
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(HOST, USER, PASS);
            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void finish(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void finish(Connection conn, PreparedStatement stmt) {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void finish(Connection conn, PreparedStatement stmt, ResultSet result) {
        try {
            result.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}