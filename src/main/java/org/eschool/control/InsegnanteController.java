package org.eschool.control;

import org.eschool.dao.AssenzaDAO;
import org.eschool.dao.ReportDAO;
import org.eschool.model.Account;
import org.eschool.model.Lezione;
import org.eschool.utils.DataReport;
import org.eschool.view.InsegnanteView;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

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
                case 2 -> produceWeeklyReport();
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

    public void produceWeeklyReport() throws SQLException {
        ReportDAO reportDAO = new ReportDAO();

        LocalDate today = LocalDate.now();
        LocalDate beginOfTheWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfTheWeek = today.with(DayOfWeek.SUNDAY).plusWeeks(1);

        List<DataReport> report = reportDAO.getWeeklyReport(id_account, beginOfTheWeek, endOfTheWeek);
        if (!report.isEmpty()) publishWeeklyReport(report);
        else System.out.println("Error producing report");

    }

    public void publishWeeklyReport(List<DataReport> r){
        //dummy method:
        //pubblico il report sul sito della scuola
        System.out.println("Report successfully published!");
    }

}
