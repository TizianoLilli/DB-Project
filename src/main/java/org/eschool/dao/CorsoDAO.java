package org.eschool.dao;

import org.eschool.model.Corso;
import org.eschool.model.Livello;
import org.eschool.utils.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;
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

    public int newCourse(String livello) throws SQLException {
        String query = "INSERT INTO corso(data_attivazione, livello) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) { //equivalente a fare ps.close() a fine try
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setString(2, livello);

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new SQLException("No ID generated for course");
        }
        return -1;
    }

}
