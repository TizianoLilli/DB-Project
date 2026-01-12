package org.eschool.control;

import org.eschool.dao.AccountDAO;
import org.eschool.model.Account;
import org.eschool.view.LoginView;

import java.sql.SQLException;
import java.util.UUID;

public class LoginController {
    private LoginView view;
    private AccountDAO accountDAO;

    public LoginController() throws SQLException {
        this.view = new LoginView();
        this.accountDAO = new AccountDAO();
    }

    private int choice;

    public Account setup() {
        boolean exit = false;

        while(!exit){
            choice = view.ShowMenu();

            switch (choice){
                case 1 -> {
                    return logIn();
                }
                case 2 -> registration();
                case 3 -> exit = true;
            }
        }

        System.out.print("\nExiting...");
        return null;
    }

    private Account account;

    public Account logIn(){
        account = view.auth();
        return accountDAO.getAccount(account.getUsername(), account.getPassword());
    }

    public void registration(){

    }

    public static String generateUsername(String name, String surname){
        return (name + "." + surname).toLowerCase();
    }

    public static String generatePassword(){
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
