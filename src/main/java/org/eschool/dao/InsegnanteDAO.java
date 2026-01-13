package org.eschool.dao;

import org.eschool.model.Corso;
import org.eschool.model.Insegnante;
import org.eschool.utils.ConnectionManager;
import org.eschool.utils.enums.Ruolo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsegnanteDAO {
    private final Connection connection;

    public InsegnanteDAO() throws SQLException {this.connection = ConnectionManager.getConnection();}

    public List<Insegnante> getAllTeachers(){
        List<Insegnante> insegnanti = new ArrayList<>();
        String query = "SELECT * FROM insegnante";

        try (PreparedStatement ps = connection.prepareStatement(query); //equivalente a fare ps.close() a fine try
             ResultSet rs = ps.executeQuery();) { //equivalente a fare rs.close() ...

            while (rs.next()){
                Insegnante insegnante = new Insegnante(rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("indirizzo"),
                        rs.getString("nazione"));
                insegnanti.add(insegnante);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return insegnanti;
    }

    public void newTeacher(String user, String pass, Insegnante insegnante) throws SQLException {

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
            throw new SQLException(e.getMessage());
        }
    }

}
