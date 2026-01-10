package org.eschool.control;

import org.eschool.dao.IscrittoDAO;
import org.eschool.model.Account;
import org.eschool.model.Iscritto;
import org.eschool.view.IscrittoView;

import java.io.IOException;
import java.sql.SQLException;

public class IscrittoController implements Controller{

    private Iscritto iscritto;
    private int id_account;
    private IscrittoView view;
    private IscrittoDAO iscrittoDAO;

    public IscrittoController (Account account) throws SQLException {
        this.id_account= account.getId();
        this.iscrittoDAO = new IscrittoDAO();
        this.iscritto = iscrittoDAO.getIscrittoFromId(id_account);
        this.view = new IscrittoView();
    }

    private int choice;

    @Override
    public void start() throws IOException {
        boolean exit = false;

        while(!exit){
            choice = view.ShowMenu();

            switch (choice){
                case 1 -> Subscribe();
                case 2 -> DeleteSubscription();
                case 3 -> exit = true;
            }
        }

        System.out.print("Exiting...");
    }

    public void Subscribe(){ //per iscriversi ad un corso
        System.out.print("Subscribed!!!");
    }

    public void DeleteSubscription(){}
}
