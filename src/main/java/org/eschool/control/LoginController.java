package org.eschool.control;

import org.eschool.model.Account;
import org.eschool.view.LoginView;

import java.io.IOException;
import java.sql.Connection;

public class LoginController implements Controller{
    private LoginView view;
    Connection connection;

    public LoginController(Connection conn){
        this.view = new LoginView();
        this.connection = conn;
    }

    private int choice;

    @Override
    public void start() throws IOException {
        boolean exit = false;

        while(!exit){
            choice = view.ShowMenu();

            switch (choice){
                case 1 -> LogIn();
                case 2 -> /*Registration();*/ System.out.print("Registrated");
                case 3 -> exit = true;
            }
        }

        System.out.print("\nExiting...");
    }

    private String user;
    private String pass;
    private Account account;
    private AccountDAO dao;

    public void LogIn(){
        account = view.auth();
        dao.getAccount();
    }
}
