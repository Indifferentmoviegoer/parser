package com.jmb.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    @Test
    void connect() throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();

        assertDoesNotThrow((Executable) connection::connect);
    }

    @Test
    void insertValues() throws SQLException {
        String data = "блоке - 1 \nв - 1 \nтекст - 1 \nслово - 15 \nТекст - 1 \n";

        DatabaseConnection connection = new DatabaseConnection();

        assertDoesNotThrow(() -> connection.insertValues(data));
    }

    @Test
    void insertValuesError() throws SQLException {
        String data = ";drop table `statistics`;";

        DatabaseConnection connection = new DatabaseConnection();

        assertDoesNotThrow(() -> connection.insertValues(data));
    }

    @Test
    void createTable() throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();

        assertDoesNotThrow(connection::createTable);
    }
}