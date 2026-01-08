package org.eschool.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static Connection conn;

    private ConnectionManager() {}

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()){
            try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {

                Properties properties = new Properties();
                properties.load(input);

                String connectionUrl = properties.getProperty("DB_URL");
                String user = properties.getProperty("USER");
                String pass = properties.getProperty("PASS");

                conn = DriverManager.getConnection(connectionUrl, user, pass);
                System.out.println("Logged in successfully!");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                    System.out.println("Logged out successfully!");
                }
            } catch (SQLException e) {
                System.err.println("Error during log out.");
                e.printStackTrace();
            }
        }
    }
}
