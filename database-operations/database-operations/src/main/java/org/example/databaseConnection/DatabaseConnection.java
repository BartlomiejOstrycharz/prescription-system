package org.example.databaseConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

        public static Connection connect() throws SQLException {
            Properties properties = new Properties();

            try (FileInputStream input = new FileInputStream("D:\\studia\\32ISM\\projektowanie-aplikacji-biznesowych\\mysql-connection\\MySQL\\database.properties.txt")) {
                properties.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String dbUrl = properties.getProperty("db.url");
            String dbUser = properties.getProperty("db.user");
            String dbPassword = properties.getProperty("db.password");

            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        }
}
