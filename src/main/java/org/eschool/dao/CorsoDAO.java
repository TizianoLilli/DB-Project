package org.eschool.dao;

import org.eschool.model.Corso;
import org.eschool.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CorsoDAO {
    private final Connection connection;

    public CorsoDAO() throws SQLException {this.connection = ConnectionManager.getConnection();}

    public List<Corso> getAllCourses(){
        List<Corso> corsi = new ArrayList<>();
        String query = "SELECT * FROM corso";

        try (PreparedStatement ps = connection.prepareStatement(query); //equivalente a fare ps.close() a fine try
             ResultSet rs = ps.executeQuery();) { //equivalente a fare rs.close() ...

            while (rs.next()){
                Corso corso = new Corso(rs.getString("id"),
                        rs.getString("livello"),
                        rs.getDate("data_attivazione").toLocalDate());
                corsi.add(corso);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return corsi;
    }

}
