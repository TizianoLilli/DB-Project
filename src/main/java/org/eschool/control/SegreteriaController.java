package org.eschool.control;

import org.eschool.dao.CorsoDAO;
import org.eschool.dao.IscrittoDAO;
import org.eschool.dao.PartecipazioneDAO;
import org.eschool.model.Account;
import org.eschool.model.Corso;
import org.eschool.model.Iscritto;
import org.eschool.utils.exception.WrongDataException;
import org.eschool.view.SegreteriaView;

import java.sql.SQLException;
import java.util.List;

public class SegreteriaController implements Controller{
    private int account_id;
    private SegreteriaView view;
    private CorsoDAO corsoDAO;
    private IscrittoDAO iscrittoDAO;
    private PartecipazioneDAO partecipazioneDAO;

    public SegreteriaController(Account account) throws SQLException {
        this.account_id = account.getId();
        this.view = new SegreteriaView();
        this.corsoDAO = new CorsoDAO();
        this.iscrittoDAO = new IscrittoDAO();
        this.partecipazioneDAO = new PartecipazioneDAO();
    }

    @Override
    public void start() throws SQLException {
        boolean exit = false;

        while(!exit){
            int choice = view.showMenu();

            switch (choice){
                case 1 -> insertSubscription();
                case 2 -> deleteSubscription();
                case 3 -> exit = true;
            }
        }

        System.out.print("Exiting...");
    }

    public void insertSubscription(){
        try{
            List<Corso> corsi = corsoDAO.getAllCourses();
            List<Iscritto> iscritti = iscrittoDAO.getAllSubscribers();

            if (!corsi.isEmpty()){
                if (!iscritti.isEmpty()) {

                    int id_corso = view.showCourses(corsi);
                    int id_iscritto = view.showSubscriber(iscritti);

                    try{
                        partecipazioneDAO.newPartecipation(id_iscritto, id_corso);
                        System.out.println("Successfully subscribed!");
                    } catch (WrongDataException e){
                        System.out.println(e.getMessage());
                    }
                }else System.out.println("No subscriber found...");

            } else System.out.println("No courses found...");
        } catch (WrongDataException e){
            System.out.println(e.getCause().getMessage());
        }

    }

    public void deleteSubscription(){
        try {
            List<Iscritto> iscritti = iscrittoDAO.getAllSubscribers();

            if (!iscritti.isEmpty()) {

                int id_iscritto = view.showSubscriber(iscritti);
                Integer id_corso = partecipazioneDAO.getCourseFromIscritto(id_iscritto);

                if (id_corso == null) System.out.println("No active subscription...");
                else {
                    int value = view.showActiveCourse(id_corso);

                    if(value == 1) {
                        partecipazioneDAO.deletePartecipation(id_iscritto, id_corso);
                        System.out.println("Subscription successfully deleted!");
                    }
                }
            } else System.out.println("No subscriber found...");
        } catch (WrongDataException e){
            System.out.println(e.getCause().getMessage());
        }

    }

}
