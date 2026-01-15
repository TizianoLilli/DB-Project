package org.eschool.dao;

import org.eschool.model.Account;
import org.eschool.model.Corso;
import org.eschool.model.Insegnante;
import org.eschool.model.Iscritto;
import org.eschool.utils.ConnectionManager;
import org.eschool.utils.enums.Ruolo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IscrittoDAO {
    private final Connection connection;

    public IscrittoDAO () throws SQLException {
        this.connection = ConnectionManager.getConnection();
    }

    public List<Iscritto> getAllSubscribers(){
        List<Iscritto> iscritti = new ArrayList<>();
        String query = "SELECT * FROM iscritto";

        try (PreparedStatement ps = connection.prepareStatement(query); //equivalente a fare ps.close() a fine try
             ResultSet rs = ps.executeQuery();) { //equivalente a fare rs.close() ...

            while (rs.next()){
                Iscritto iscritto = new Iscritto(
                        rs.getInt("id"),
                        rs.getString("cf"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getDate("data_nascita").toLocalDate(),
                        rs.getString("indirizzo"),
                        rs.getString("recapito"));
                iscritti.add(iscritto);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return iscritti;
    }

    public Iscritto getIscrittoFromId(int id){
        String query = "SELECT * FROM iscritto WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query);) { //equivalente a fare ps.close() a fine try
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return new Iscritto(
                        id,
                        rs.getString("cf"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getDate("data_nascita").toLocalDate(),
                        rs.getString("indirizzo"),
                        rs.getString("recapito")
                        );
            }
            rs.close(); //chiudo il result set e rilascio le risorse

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void newSub(String user, String pass, Iscritto iscritto) throws SQLException {

        String query = "{CALL new_subscriber(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement cs = connection.prepareCall(query)) {

            cs.setString(1, user);
            cs.setString(2, pass);
            cs.setInt(3, Ruolo.ISCRITTO.getIdx());
            cs.setString(4, iscritto.getCf());
            cs.setString(5, iscritto.getNome());
            cs.setString(6, iscritto.getCognome());
            cs.setDate(7, Date.valueOf(iscritto.getData_nascita()));
            cs.setString(8, iscritto.getIndirizzo());
            cs.setString(9, iscritto.getRecapito());

            cs.executeQuery();

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

}
