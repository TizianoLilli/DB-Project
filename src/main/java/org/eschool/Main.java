package org.eschool;

import org.eschool.control.Controller;
import org.eschool.control.LoginController;
import org.eschool.utils.ConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    static void main() {

        try {
            Connection connection = ConnectionManager.getConnection();

            System.out.print("Hello and Welcome to ILearn!\n");
            Controller controller = new LoginController(connection);
            controller.start();

        } catch (SQLException e) {
            System.err.println("Error during DB disconnection: " + e.getMessage());
            ConnectionManager.closeConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
