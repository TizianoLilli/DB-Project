package org.eschool.control;

import org.eschool.dao.AccountDAO;
import org.eschool.dao.IscrittoDAO;
import org.eschool.model.Account;
import org.eschool.model.Iscritto;
import org.eschool.view.IscrittoView;
import org.eschool.view.LoginView;

import java.sql.SQLException;
import java.util.UUID;

public class LoginController {
    private LoginView view;
    private AccountDAO accountDAO;
    private IscrittoDAO iscrittoDAO;

    public LoginController() throws SQLException {
        this.view = new LoginView();
        this.accountDAO = new AccountDAO();
        this.iscrittoDAO = new IscrittoDAO();
    }

    private int choice;

    public Account setup() throws SQLException {
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

    public void registration(){ //per registrare un nuovo iscritto
        Iscritto iscritto = view.register();

        String user = generateUsername(iscritto.getNome(), iscritto.getCognome());
        String pass = generatePassword(); //password temporanea

        try{
            iscrittoDAO.newSub(user, pass, iscritto);
            System.out.println("Subscriber successfully inserted!");
            sendCredentials(user, pass, iscritto.getRecapito());
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public static String generateUsername(String name, String surname){
        return (name + "." + surname).toLowerCase();
    }

    public static String generatePassword(){
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public void sendCredentials(String u, String p, String d){
        /* dummy method:
        email the subscriber with the new credentials
         */
    }
}
