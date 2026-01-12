package org.eschool.dao;

import org.eschool.model.Corso;
import org.eschool.model.Livello;
import org.eschool.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivelloDAO {
    private final Connection connection;

    public LivelloDAO() throws SQLException {this.connection = ConnectionManager.getConnection();}

    public List<Livello> getAllLevels(){
        List<Livello> livelli = new ArrayList<>();
        String query = "SELECT * FROM livello";

        try (PreparedStatement ps = connection.prepareStatement(query); //equivalente a fare ps.close() a fine try
             ResultSet rs = ps.executeQuery();) { //equivalente a fare rs.close() ...

            while (rs.next()){
                Livello livello = new Livello(rs.getString("nome"),
                        rs.getString("libro"),
                        rs.getBoolean("esame"));
                livelli.add(livello);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return livelli;
    }
}
