package org.eschool.dao;

import org.eschool.utils.ConnectionManager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AfferenzaDAO {
    private final Connection connection;

    public AfferenzaDAO() throws SQLException {this.connection = ConnectionManager.getConnection();}

    public void newReference(int id_corso, int id_insegnante) throws SQLException {
        String sql = "INSERT INTO afferenza VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) { //equivalente a fare cs.close() a fine try

            ps.setInt(1, id_corso);
            ps.setInt(2, id_insegnante);

            ps.execute();

        } catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }
}
