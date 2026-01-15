package org.eschool.dao;

import org.eschool.utils.DataReport;
import org.eschool.utils.ConnectionManager;
import org.eschool.utils.enums.StatoLezione;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {
    private final Connection connection;

    public ReportDAO() throws SQLException {this.connection = ConnectionManager.getConnection();}

    public List<DataReport> getWeeklyReport(int insegnante, LocalDate begin, LocalDate end){ //chiamato dall'insegnante
        List<DataReport> report = new ArrayList<>();
        String query = "SELECT corso, erogata, data, ora, assente FROM report WHERE insegnante = ? AND data BETWEEN ? AND ? ORDER BY data, ora, corso";

        try (PreparedStatement ps = connection.prepareStatement(query)){ //equivalente a fare ps.close() a fine try

            ps.setInt(1, insegnante);
            ps.setDate(2, Date.valueOf(begin));
            ps.setDate(3, Date.valueOf(end));

            ResultSet rs = ps.executeQuery();

            DataReport current = null;
            while (rs.next()) {
                int corso = rs.getInt("corso");
                StatoLezione stato_lezione = StatoLezione.fromState(rs.getInt("erogata"));
                LocalDate data = rs.getDate("data").toLocalDate();
                LocalTime ora = rs.getTime("ora").toLocalTime();
                int assente = rs.getInt("assente");

                if (current == null ||
                        current.getCorso() != corso ||
                        !current.getData_lezione().equals(data) ||
                        !current.getOra_lezione().equals(ora)) {

                    current = new DataReport(corso, stato_lezione, data, ora);
                    report.add(current);
                }

                current.getAssenti().add(assente);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return report;
    }

    public List<DataReport> getMonthlyReport(LocalDate begin, LocalDate end){ //chiamato dal personale amministrativo
        List<DataReport> report = new ArrayList<>();
        String query = "SELECT insegnante, corso, erogata, data, ora, assente FROM report WHERE data BETWEEN ? AND ? ORDER BY data, ora, corso";

        try (PreparedStatement ps = connection.prepareStatement(query)){ //equivalente a fare ps.close() a fine try

            ps.setDate(1, Date.valueOf(begin));
            ps.setDate(2, Date.valueOf(end));

            ResultSet rs = ps.executeQuery();

            DataReport current = null;
            while (rs.next()) {
                int insegnante = rs.getInt("insegnante");
                int corso = rs.getInt("corso");
                StatoLezione stato_lezione = StatoLezione.fromState(rs.getInt("erogata"));
                LocalDate data = rs.getDate("data").toLocalDate();
                LocalTime ora = rs.getTime("ora").toLocalTime();
                int assente = rs.getInt("assente");

                if (current == null ||
                        current.getInsegnante() != insegnante ||
                        current.getCorso() != corso ||
                        !current.getData_lezione().equals(data) ||
                        !current.getOra_lezione().equals(ora)) {

                    current = new DataReport(insegnante, corso, stato_lezione, data, ora);
                    report.add(current);
                }

                current.getAssenti().add(assente);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return report;
    }
}
