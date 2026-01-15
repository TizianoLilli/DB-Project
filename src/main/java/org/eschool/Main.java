package org.eschool;

import org.eschool.control.*;
import org.eschool.model.Account;
import org.eschool.utils.ConnectionManager;
import org.eschool.view.SegreteriaView;

import java.sql.SQLException;

public class Main {
    static void main() {

        try {

            System.out.print("Hello and Welcome to ILearn!\n");
            LoginController controller = new LoginController();

            boolean ok = false;
            while (!ok){
                ok = true;
                Account account = controller.setup();

                switch (account.getRole()){
                    case ISCRITTO -> new IscrittoController(account).start();
                    case INSEGNANTE -> new InsegnanteController(account).start();
                    case PERSONALE_AMMINISTRATIVO -> new AdminController(account).start();
                    case PERSONALE_SEGRETERIA -> new SegreteriaController(account).start();
                    default -> ok = false;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error during DB disconnection: " + e.getMessage());
            ConnectionManager.closeConnection();
        }



    }
}
