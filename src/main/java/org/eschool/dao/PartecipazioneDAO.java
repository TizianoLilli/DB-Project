package org.eschool.dao;

import org.eschool.model.Corso;
import org.eschool.utils.ConnectionManager;
import org.eschool.utils.exception.WrongDataException;

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
        } catch (SQLException e) {
            throw new WrongDataException("Error during course subscription", e);
        }
    }

    public Integer getCourseFromIscritto(int id){
        String query = "SELECT corso FROM partecipazione WHERE iscritto = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return rs.getInt("corso");
                }
            }

        } catch (SQLException e) {
            throw new WrongDataException("Get course error", e);
        }
        return null;
    }

    public void deletePartecipation(int id_iscritto, int id_corso){
        String sql = "{CALL delete_partecipation(?, ?)}";

        try (CallableStatement cs = connection.prepareCall(sql)) {

            cs.setInt(1, id_iscritto);
            cs.setInt(2, id_corso);

            cs.execute();
        } catch (SQLException e) {
            throw new WrongDataException("Delete participation error", e);
        }
    }

}
