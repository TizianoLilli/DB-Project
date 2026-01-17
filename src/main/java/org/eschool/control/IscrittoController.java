package org.eschool.control;

import org.eschool.dao.CorsoDAO;
import org.eschool.dao.IscrittoDAO;
import org.eschool.dao.PartecipazioneDAO;
import org.eschool.model.Account;
import org.eschool.model.Corso;
import org.eschool.model.Iscritto;
import org.eschool.utils.exception.WrongDataException;
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

    @Override
    public void start() throws SQLException {
        boolean exit = false;

        while(!exit){
            int choice = view.showMenu();

            switch (choice){
                case 1 -> viewCourses();
                case 2 -> viewActiveCourse();
                case 3 -> exit = true;
            }
        }

        System.out.print("Exiting...");
    }

    public void viewCourses() { //per vedere corsi
        try {
            List<Corso> corsi = corsoDAO.getAllCourses();

            if (!corsi.isEmpty()){
                view.showCourses(corsi);
            } else System.out.println("No courses found...");
        } catch (WrongDataException e){
            System.out.println(e.getCause().getMessage());
        }

    }

    public void viewActiveCourse(){
        try {
            Integer id_corso = partecipazioneDAO.getCourseFromIscritto(id_account);

            if (id_corso == null) System.out.println("No active subscription...");
            else {
                view.showActiveCourse(id_corso);
            }
        } catch (WrongDataException e){
            System.out.println(e.getCause().getMessage());
        }

    }
}
