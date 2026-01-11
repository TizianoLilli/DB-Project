package org.eschool.control;

import org.eschool.dao.CorsoDAO;
import org.eschool.dao.IscrittoDAO;
import org.eschool.dao.PartecipazioneDAO;
import org.eschool.model.Account;
import org.eschool.model.Corso;
import org.eschool.model.Iscritto;
import org.eschool.view.IscrittoView;

import java.sql.SQLException;
import java.util.List;

public class IscrittoController implements Controller{

    private Iscritto iscritto;
    private int id_account;
    private IscrittoView view;
    private IscrittoDAO iscrittoDAO;
    private CorsoDAO corsoDAO;
    private PartecipazioneDAO partecipazioneDAO;

    public IscrittoController (Account account) throws SQLException {
        this.id_account= account.getId();
        this.iscrittoDAO = new IscrittoDAO();
        this.iscritto = iscrittoDAO.getIscrittoFromId(id_account);
        this.view = new IscrittoView();
        this.corsoDAO = new CorsoDAO();
        this.partecipazioneDAO = new PartecipazioneDAO();
    }

    private int choice;

    @Override
    public void start() throws SQLException {
        boolean exit = false;

        while(!exit){
            choice = view.showMenu();

            switch (choice){
                case 1 -> subscribe();
                case 2 -> deleteSubscription();
                case 3 -> exit = true;
            }
        }

        System.out.print("Exiting...");
    }

    public void subscribe() throws SQLException { //per iscriversi ad un corso
        List<Corso> corsi = corsoDAO.getAllCourses();

        if (!corsi.isEmpty()){
            int id_corso = view.showCourses(corsi);

            try{
                partecipazioneDAO.newPartecipation(id_account, id_corso);
                System.out.println("Successfully subscribed!");
            } catch (Exception e){
                System.out.println("Error during course subscription");
                System.out.println(e.getMessage());
            }


        } else System.out.println("No courses found...");

    }

    public void deleteSubscription(){
        int id_corso = partecipazioneDAO.getCourseFromIscritto(id_account);

        if (id_corso == -1) System.out.println("No active subscription...");
        else {
            int value = view.showActiveCourse(id_corso);

            if(value == 1) {
                partecipazioneDAO.deletePartecipation(id_account, id_corso);
                System.out.println("Successfully deleted subscription!");
            }
        }
    }
}
