package org.eschool.dao;

import org.eschool.model.Corso;
import org.eschool.model.Insegnante;
import org.eschool.utils.ConnectionManager;
import org.eschool.utils.enums.Ruolo;

import java.sql.*;

public class InsegnanteDAO {
    private final Connection connection;

    public InsegnanteDAO() throws SQLException {this.connection = ConnectionManager.getConnection();}

    public void newTeacher(String user, String pass, Insegnante insegnante){

        String query = "{CALL new_teacher(?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement cs = connection.prepareCall(query)) {

            cs.setString(1, user);
            cs.setString(2, pass);
            cs.setInt(3, Ruolo.INSEGNANTE.getIdx());
            cs.setString(4, insegnante.getNome());
            cs.setString(5, insegnante.getCognome());
            cs.setString(6, insegnante.getIndirizzo());
            cs.setString(7, insegnante.getNazione());

            cs.executeQuery();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
