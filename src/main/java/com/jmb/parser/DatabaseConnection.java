package com.jmb.parser;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

/**
 * Класс для работы с базой данных
 *
 * @author Иван Волощенко
 */
public class DatabaseConnection {

    /** Константа для чтения данных из файла конфигурации */
    private static final Dotenv DOTENV = Dotenv.load();

    /** Конструктор класса com.jmb.parser.DatabaseConnection */
    public DatabaseConnection() throws SQLException {
        this.createTable();
    }

    /**
     * Создает соединение с базой данных
     *
     * @return Текущее соединение
     */
    public Connection connect() throws SQLException {
        return DriverManager
                .getConnection(
                        DOTENV.get("MYSQL_CONNECTION"),
                        DOTENV.get("MYSQL_USERNAME"),
                        DOTENV.get("MYSQL_PASSWORD")
                );
    }

    /**
     * Сохранение статистики в базу данных
     *
     * @param data Ссылка на страницу/путь к файлу
     */
    public void insertValues(String data) {
        try {
            Connection conn = this.connect();

            PreparedStatement insertStatement = conn.prepareStatement(
                    "INSERT INTO `statistics` (data)" +
                            " VALUES (?);"
            );
            insertStatement.setString(1, data);
            insertStatement.executeUpdate();

            conn.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /** Низкоуровневое подобие миграции */
    public void createTable() {
        try {
            Connection conn = this.connect();

            PreparedStatement createStatement = conn.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS `statistics` (" +
                            "  `id` int(11) NOT NULL auto_increment," +
                            "  `data` text NOT NULL," +
                            "   PRIMARY KEY  (`id`)" +
                            ");"
            );
            createStatement.executeUpdate();

            conn.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
