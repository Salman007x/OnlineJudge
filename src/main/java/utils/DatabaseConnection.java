package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection getConnection() {

        Connection connection = null;

        try {

            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/online_judge",
                    "postgres",
                    "1234@"
            );

            System.out.println("Database Connected Successfully!");

        } catch (Exception e) {

            System.out.println("Connection Failed!");

            e.printStackTrace();
        }

        return connection;
    }
}