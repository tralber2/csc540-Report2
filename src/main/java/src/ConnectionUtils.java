package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    private static Connection connection = null;

    private static final String url = "jdbc:mysql://localhost/project_2";

    public Connection getConnection() {
        return connection;
    }

    public static void init() {
        try {
            connection = DriverManager.getConnection(url, "root", "");
            System.out.println("Connected to database: " + url);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to database: " + url);
        }
    }

    public static void finish() {
        try {
            connection.close();
            System.out.println("Closed connection to database: " + url);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to close connection to database: " + url);
        }
    }
}
