package org.eschool.dao;

import org.eschool.model.Account;
import org.eschool.model.Iscritto;
import org.eschool.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IscrittoDAO {
    private final Connection connection;

    public IscrittoDAO () throws SQLException {
        this.connection = ConnectionManager.getConnection();
    }

    public Iscritto getIscrittoFromId(int id){
        String query = "SELECT * FROM iscritto WHERE id_account = ?";

        try{
            PreparedStatement ps = connection.prepareStatement(query);
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
