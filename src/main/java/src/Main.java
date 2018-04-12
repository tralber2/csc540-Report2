package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static final String url = "jdbc:mysql://localhost/project_2";

    private static Connection connection;

    public static void main(String args[]) {
        init();
        executeMain();
        finish();
    }

    private static void init() {
        try {
            connection = DriverManager.getConnection(url, "root", "");
            System.out.println("Connected to database: " + url);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to database: " + url);
        }
    }

    private static void executeMain() {
    }

    private static void finish() {
        try {
            connection.close();
            System.out.println("Closed connection to database: " + url);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to close connection to database: " + url);
        }
    }
}
