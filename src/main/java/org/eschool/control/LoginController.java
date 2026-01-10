package org.eschool.control;

import org.eschool.dao.AccountDAO;
import org.eschool.model.Account;
import org.eschool.view.LoginView;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    private LoginView view;
    private AccountDAO accountDAO;

    public LoginController() throws SQLException {
        this.view = new LoginView();
        this.accountDAO = new AccountDAO();
    }

    private int choice;

    public Account setup() throws IOException {
        boolean exit = false;

        while(!exit){
            choice = view.ShowMenu();

            switch (choice){
                case 1 -> {
                    return LogIn();
                }
                case 2 -> /*Registration();*/ System.out.print("Registrated");
                case 3 -> exit = true;
            }
        }

        System.out.print("\nExiting...");
        return null;
    }

    private Account account;

    public Account LogIn(){
        account = view.auth();
        return accountDAO.getAccount(account.getUsername(), account.getPassword());
    }
}
