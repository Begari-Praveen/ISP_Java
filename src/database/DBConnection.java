package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/ispdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "PRAVEEN"; // change if needed

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database Connected Successfully!");
            return con;
        } catch (Exception e) {
            System.out.println("Database Connection Failed");
            e.printStackTrace();
        }
        return null;
    }

    // Quick test helper. Run this class directly to confirm the database connection.
    public static void main(String[] args) {
        try (Connection c = getConnection()) {
            if (c != null && !c.isClosed()) {
                System.out.println("Connection test passed.");
            } else {
                System.out.println("Connection test failed.");
            }
        } catch (Exception e) {
            System.out.println("Connection test failed (exception)");
            e.printStackTrace();
        }
    }
}

