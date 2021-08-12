package com.jmb.parser;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class DatabaseConnection {
    private static final Dotenv DOTENV = Dotenv.load();
    private Connection conn;

    public DatabaseConnection() throws SQLException {
        this.createTable();
    }

    public void connect() throws SQLException {
        this.conn = DriverManager
                .getConnection(
                        DOTENV.get("MYSQL_CONNECTION"),
                        DOTENV.get("MYSQL_USERNAME"),
                        DOTENV.get("MYSQL_PASSWORD")
                );
    }

    public void insertValues(String data) {
        try {
            this.connect();
            PreparedStatement insertStatement = conn.prepareStatement(
                    "INSERT INTO statistics (data)" +
                            " VALUES (?);"
            );
            insertStatement.setString(1, data);
            insertStatement.executeUpdate();

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTable() {
        try {
            this.connect();
            PreparedStatement createStatement = conn.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS `statistics` (" +
                            "  `id` int(11) NOT NULL auto_increment," +
                            "  `data` text NOT NULL," +
                            "   PRIMARY KEY  (`id`)" +
                            ");"
            );
            createStatement.executeUpdate();

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
