package org.eschool.dao;

import org.eschool.model.Corso;
import org.eschool.utils.ConnectionManager;

import java.sql.*;

public class PartecipazioneDAO {
    private final Connection connection;

    public PartecipazioneDAO() throws SQLException {this.connection = ConnectionManager.getConnection();}

    public void newPartecipation(int id_iscritto, int id_corso){
        String sql = "{CALL new_partecipation(?, ?)}";

        try (CallableStatement cs = connection.prepareCall(sql)) { //equivalente a fare cs.close() a fine try

            cs.setInt(1, id_iscritto);
            cs.setInt(2, id_corso);

            cs.execute();
        } catch (SQLException e){
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public int getCourseFromIscritto(int id){
        String query = "SELECT corso FROM partecipazione WHERE iscritto = ?";
        int value = -1;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                value = rs.getInt("corso");
            }
            rs.close(); //chiudo il result set e rilascio le risorse

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return value;
    }

    public void deletePartecipation(int id_iscritto, int id_corso){
        String sql = "{CALL delete_partecipation(?, ?)}";

        try (CallableStatement cs = connection.prepareCall(sql)) {

            cs.setInt(1, id_iscritto);
            cs.setInt(2, id_corso);

            cs.execute();
        } catch (SQLException e){
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
