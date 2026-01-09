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
                System.out.println("Successfully connected to DB!");

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
                    System.out.println("Successfully disconnected to DB!");
                }
            } catch (SQLException e) {
                System.err.println("Error during DB disconnection.");
                e.printStackTrace();
            }
        }
    }
}
