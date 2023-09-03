package br.com.rpriolo.controller;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection conectar() {
        try {
            return createDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar no banco de dados.");
        }
    }

    private HikariDataSource createDataSource() {
        String mysqlUsername = System.getenv("MYSQL_DATABASE_USERNAME");
        String mysqlPassword = System.getenv("MYSQL_DATABASE_PASSWORD");

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/agenda");
        config.setUsername(mysqlUsername);
        config.setPassword(mysqlPassword);
        config.setMaximumPoolSize(10);

        return new HikariDataSource(config);
    }
}