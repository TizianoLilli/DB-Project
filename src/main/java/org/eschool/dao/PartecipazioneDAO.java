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

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return rs.getInt("corso");
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new RuntimeException();
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
