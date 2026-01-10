package org.eschool.dao;

import org.eschool.model.Account;
import org.eschool.utils.ConnectionManager;
import org.eschool.utils.Ruolo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
    private final Connection connection;

    public AccountDAO() throws SQLException {
        this.connection = ConnectionManager.getConnection();
    }

    public Account getAccount(String user, String pass){
        String query = "SELECT * FROM account WHERE username = ? AND Password = ?";

        try{
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                int idx = rs.getInt("role");
                Ruolo role = Ruolo.fromIdx(idx);

                return new Account(rs.getInt("id"), user, pass, role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.print("No account found...");
        return null;
    }
}
