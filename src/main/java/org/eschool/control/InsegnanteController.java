package org.eschool.control;

import org.eschool.dao.AssenzaDAO;
import org.eschool.model.Account;
import org.eschool.model.Lezione;
import org.eschool.view.InsegnanteView;

import java.sql.SQLException;

public class InsegnanteController implements Controller{
    private int id_account;
    private InsegnanteView view;

    public InsegnanteController(Account account){
        this.id_account= account.getId();
        this.view = new InsegnanteView();
    }

    @Override
    public void start() throws SQLException {
        int choice;
        boolean exit = false;

        while(!exit){
            choice = view.showMenu();

            switch (choice){
                case 1 -> assignAbsence();
                case 2 -> produceReport();
                case 3 -> exit = true;
            }
        }

        System.out.print("Exiting...");
    }

    public void assignAbsence() throws SQLException {
        Lezione lezione = view.lessonForm();
        lezione.setInsegnante(id_account);

        int id_iscritto = view.subscriberForm();
        AssenzaDAO assenzaDAO = new AssenzaDAO();

        try{
            assenzaDAO.newAbsence(id_iscritto, lezione.getCorso(), lezione.getData_lezione(), lezione.getOra_lezione());
            System.out.println("Absence successfully inserted!");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }

    public void produceReport(){}

}
