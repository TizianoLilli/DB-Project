package org.eschool.dao;

import org.eschool.model.Iscritto;
import org.eschool.utils.ConnectionManager;
import org.eschool.utils.enums.Ruolo;
import org.eschool.utils.exception.WrongDataException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AssenzaDAO {
    private final Connection connection;

    public AssenzaDAO() throws SQLException {this.connection = ConnectionManager.getConnection();}

    public void newAbsence(int id_iscritto, int id_corso, LocalDate data, LocalTime ora) {

        String query = "{CALL new_absence(?, ?, ?, ?)}";

        try (CallableStatement cs = connection.prepareCall(query)) {

            cs.setInt(1, id_iscritto);
            cs.setInt(2, id_corso);
            cs.setDate(3, Date.valueOf(data));
            cs.setTime(4, Time.valueOf(ora));

            cs.executeQuery();

        } catch (SQLException e) {
            throw new WrongDataException("New absence error", e);
        }
    }

}
