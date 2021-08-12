package com.jmb.parser;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        System.out.print("Введите адрес сайта: ");
        String url = in.nextLine();

        Parser parser = new Parser(url);
        try {
            StringBuilder result = parser.run();
            DatabaseConnection connection = new DatabaseConnection();
            connection.insertValues(result.toString());
        } catch (IllegalArgumentException | SQLException exception) {
            LOG.error("Url: {}  is not correct", url);
        }
        LOG.info("Ending application.");
    }
}
