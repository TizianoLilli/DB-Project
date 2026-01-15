package org.eschool.control;

import org.eschool.dao.*;
import org.eschool.model.Account;
import org.eschool.model.Corso;
import org.eschool.model.Insegnante;
import org.eschool.model.Livello;
import org.eschool.utils.DataReport;
import org.eschool.view.AdminView;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.eschool.control.LoginController.generatePassword;
import static org.eschool.control.LoginController.generateUsername;

public class AdminController implements Controller {
    private int id_account;
    private AdminView view;
    private LivelloDAO livelloDAO;
    private CorsoDAO corsoDAO;
    private InsegnanteDAO insegnanteDAO;
    private AfferenzaDAO afferenzaDAO;

    public AdminController (Account account) throws SQLException {
        this.id_account = account.getId();
        this.view = new AdminView();
        this.livelloDAO = new LivelloDAO();
        this.corsoDAO = new CorsoDAO();
        this.insegnanteDAO = new InsegnanteDAO();
        this.afferenzaDAO = new AfferenzaDAO();
    }

    @Override
    public void start() throws SQLException {
        boolean exit = false;

        while(!exit){
            int choice = view.showMenu();

            switch (choice){
                case 1 -> insertCourse();
                case 2 -> insertTeacher();
                case 3 -> assignTeacher();
                case 4 -> produceMonthlyReport();
                case 5 -> exit = true;
            }
        }

        System.out.print("Exiting...");
    }

    public void insertCourse(){ //v

        List<Livello> livelli = livelloDAO.getAllLevels();

        if (!livelli.isEmpty()){
            String nome_livello = view.courseForm(livelli);

            try{
                int id_corso = corsoDAO.newCourse(nome_livello);
                System.out.printf("Course successfully inserted!\nID: %d\n", id_corso);
            } catch (SQLException e){
                System.out.println("Error during course insertion");
                System.out.println(e.getMessage());
            }


        } else System.out.println("No levels found...");

    }

    public void insertTeacher() throws SQLException {
        Insegnante insegnante = view.teacherForm();
        InsegnanteDAO insegnanteDAO = new InsegnanteDAO();

        String user = generateUsername(insegnante.getNome(), insegnante.getCognome());
        String pass = generatePassword(); //password temporanea

        try{
            insegnanteDAO.newTeacher(user, pass, insegnante);
            System.out.println("Teacher successfully inserted!");
            sendCredentials(user, pass);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }

    public void sendCredentials(String u, String p){
        /* dummy method:
        email the teacher with the new credentials
         */
    }

    public void assignTeacher(){
        List<Corso> corsi = corsoDAO.getAllCourses();
        List<Insegnante> insegnanti = insegnanteDAO.getAllTeachers();

        if (corsi.isEmpty()) System.out.println("No courses found...");
        else if (insegnanti.isEmpty()) System.out.println("No teachers found...");
        else {

            try{
                int id_corso = view.referenceToCourse(corsi);
                int id_insegnante = view.referenceToTeacher(insegnanti);
                afferenzaDAO.newReference(id_corso, id_insegnante);
                System.out.println("Reference successfully inserted!");
            } catch (SQLException e){
                System.out.println("Error during reference insertion");
                System.out.println(e.getMessage());
            }

        }

    }

    public void produceMonthlyReport() throws SQLException {
        ReportDAO reportDAO = new ReportDAO();

        LocalDate today = LocalDate.now();
        LocalDate beginOfTheMonth = today.withDayOfMonth(1);
        LocalDate endOfTheMonth = today.plusMonths(1).withDayOfMonth(today.plusMonths(1).lengthOfMonth());

        List<DataReport> report = reportDAO.getMonthlyReport(beginOfTheMonth, endOfTheMonth);
        if (!report.isEmpty()) {
            view.showMonthlyReport(report);
            publishMonthlyReport(report);
        }
        else System.out.println("Error producing report");

    }

    public void publishMonthlyReport(List<DataReport> r){
        //dummy method:
        //pubblico il report sul sito della scuola
        System.out.println("Report successfully published!");
    }

}
