package com.jmb.parser;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class.getName());
    private static final String PROPERTIES = "src/main/resources/log4j.properties";

    public static void main(String[] args) throws IOException {
        LOG.info("Entering application.");

        PropertyConfigurator.configure(PROPERTIES);

        Scanner in = new Scanner(System.in);
        System.out.print("Введите ссылку: ");
        String url = in.nextLine();

        Parser parser = new Parser(url);
        try {
            String result = parser.parseFromUrl();

            DatabaseConnection connection = new DatabaseConnection();
            connection.insertValues(result);
        } catch (IllegalArgumentException | SQLException | FileNotFoundException exception) {
            LOG.error(String.valueOf(exception));
        }

        LOG.info("Ending application.");
    }
}
